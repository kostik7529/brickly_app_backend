package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.UserCreateDTO;
import ru.brickly.core.dto.UserDefaultDTO;
import ru.brickly.core.dto.UserFullDTO;

import java.util.List;

public interface UserService {
    List<UserDefaultDTO> getAllUsers();

    Page<UserDefaultDTO> getAllUsersPaginated(Pageable pageable);

    UserDefaultDTO getUserById(Long id);

    UserDefaultDTO getUserByUsername(String login);

    UserDefaultDTO createUser(UserCreateDTO dto);

    UserFullDTO updateUser(Long id, UserFullDTO dto);

    void deleteUser(Long id);
}
