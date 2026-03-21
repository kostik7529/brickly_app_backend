package ru.brickly.core.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.brickly.core.dto.UserCreateDTO;
import ru.brickly.core.dto.UserDefaultDTO;
import ru.brickly.core.dto.UserFullDTO;
import ru.brickly.core.dto.UserUpdateDTO;
import ru.brickly.core.entity.Authority;
import ru.brickly.core.entity.User;
import ru.brickly.core.exception.UserAlreadyExistsException;
import ru.brickly.core.exception.UserNotFoundException;
import ru.brickly.core.repository.AuthorityRepository;
import ru.brickly.core.repository.UserRepository;
import ru.brickly.core.service.UserService;
import ru.brickly.core.util.UserMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public List<UserDefaultDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::convertToDefaultDto).collect(Collectors.toList());
    }

    @Override
    public Page<UserDefaultDTO> getAllUsersPaginated(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserMapper::convertToDefaultDto);
    }

    @Override
    public UserDefaultDTO getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::convertToDefaultDto).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found!"));
    }

    @Override
    public UserDefaultDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(UserMapper::convertToDefaultDto).orElseThrow(() -> new UserNotFoundException("User with id " + username + " not found!"));
    }

    @Override
    public UserDefaultDTO createUser(UserCreateDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with username " + dto.getUsername() +  "already exists!");
        }

        Optional<Authority> roleUser = authorityRepository.findByAuthority("ROLE_USER");
        if (roleUser.isEmpty()) {
            throw new RuntimeException("Authority not found!");
        }

        User user = new User();
        user.setCreatedAt(dto.getCreatedAt());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAuthorities(Set.of(roleUser.get()));
        return UserMapper.convertToDefaultDto(userRepository.save(user));
    }

    @Override
    public UserFullDTO updateUser(Long id, UserUpdateDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()
                && Objects.equals(userRepository.findByUsername(dto.getUsername()).get().getUsername(),
                userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found!")).getUsername())) {
            throw new UserAlreadyExistsException("User with username " + dto.getUsername() +  " already exists!");
        }

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found!"));

        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }

        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        if (dto.getCity() != null) {
            user.setCity(dto.getCity());
        }

        return UserMapper.convertToFullDto(userRepository.save(user));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found!"));
        userRepository.deleteById(id);
    }
}
