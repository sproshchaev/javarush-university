package com.javarush.controller;

import com.javarush.dto.CreateUserDto;
import com.javarush.dto.UpdateUserDto;
import com.javarush.dto.UserDto;
import com.javarush.entity.User;
import com.javarush.exception.BusinessException;
import com.javarush.exception.ResourceNotFoundException;
import com.javarush.repository.UserRepository;
import com.javarush.service.RegistrationService;
import com.javarush.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor // вместо конструктора с внедрением зависимостей
// @AllArgsConstructor
public class HelloController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    @PostMapping("/users/update-emails")
    public ResponseEntity<String> updateEmails(
            @RequestParam Long id1,
            @RequestParam Long id2,
            @RequestParam String email1,
            @RequestParam String email2) {

        userService.updateUserEmails(id1, id2, email1, email2);
        return ResponseEntity.ok("Emails updated successfully");
    }

    @PostMapping("/users/{id}/email")
    public ResponseEntity<String> updateEmailWithChecked(@PathVariable Long id, @RequestParam String email) {
        try {
            userService.updateUserEmailWithChecked(id, email);
            return ResponseEntity.ok("Email updated successfully");
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        // Используем readOnly-метод сервиса
        return userService.getAllUsers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String name, @RequestParam String email) {
        try {
            registrationService.registerUser(name, email);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/users")
    // @ResponseStatus(HttpStatus.CREATED) - заменяет ResponseEntity.created
    // ДОБАВЛЕНО ДЛЯ ДЕМО: @Valid и BindingResult
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDto createUserDto,
                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        User user = new User(createUserDto.getName(),
                createUserDto.getEmail());

        User saved = userRepository.save(user);

        UserDto userDto = new UserDto(saved.getId(),
                saved.getName(),
                saved.getEmail());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(userDto);
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                // ДОБАВЛЕНО ДЛЯ ДЕМО: было new RuntimeException(...)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return convertToDto(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        User existingUser = userRepository.findById(id)
                // ДОБАВЛЕНО ДЛЯ ДЕМО: было new RuntimeException(...)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existingUser.setName(updateUserDto.getName());
        existingUser.setEmail(updateUserDto.getEmail());

        User saved = userRepository.save(existingUser);
        UserDto responseDto = new UserDto(saved.getId(),
                saved.getName(),
                saved.getEmail());

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(),
                user.getName(),
                user.getEmail());
    }


}
