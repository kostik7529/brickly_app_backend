package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.brickly.core.entity.User;
import ru.brickly.core.exception.UserNotFoundException;
import ru.brickly.core.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);


        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        return optionalUser.get();
    }
}
