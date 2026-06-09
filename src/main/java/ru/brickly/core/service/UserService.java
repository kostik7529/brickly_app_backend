package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.*;

import java.util.List;

public interface UserService {
    List<UserDefaultDTO> getAllUsers();

    Page<UserDefaultDTO> getAllUsersPaginated(Pageable pageable);

    Page<UserDefaultDTO> getUsersByUsernameContaining(String usernameContaining, Pageable pageable);

    UserDefaultDTO getUserById(Long id);

    UserDefaultDTO getUserByUsername(String login);

    UserDefaultDTO createUser(UserCreateDTO dto);

    UserFullDTO updateUser(Long id, UserUpdateDTO dto);

    void deleteUserById(Long id);

    UserDefaultDTO changeUserAuthorities(long id, UserAuthoritiesPatchDTO dto);
}
