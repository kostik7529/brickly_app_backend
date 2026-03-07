package ru.brickly.core.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.UserCreateDTO;
import ru.brickly.core.dto.UserDefaultDTO;
import ru.brickly.core.dto.UserFullDTO;
import ru.brickly.core.dto.UserUpdateDTO;
import ru.brickly.core.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/app/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDefaultDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<UserDefaultDTO>> getAllUsersPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersPaginated(pageable));
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity<UserDefaultDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @GetMapping("/by_username/{username}")
    public ResponseEntity<UserDefaultDTO> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/exists/{username}")
    public ResponseEntity<String> checkUserExistence(@PathVariable String username) {
        userService.getUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body("User with username " + username + " exists");
    }

    @PostMapping("/register")
    public ResponseEntity<UserDefaultDTO> createUser(@RequestBody UserCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserFullDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, dto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
