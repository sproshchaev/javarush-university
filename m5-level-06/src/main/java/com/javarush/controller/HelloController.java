package com.javarush.controller;

import com.javarush.entity.User;
import com.javarush.exception.BusinessException;
import com.javarush.repository.UserRepository;
import com.javarush.service.RegistrationService;
import com.javarush.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        userService.updateUserEmails(id1, id2, email1, email2, false);
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
    public List<User> getUsers() {
        // Используем readOnly-метод сервиса
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String name, @RequestParam String email) {
        try {
            registrationService.registerUser(name, email, false);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Registration failed: " + e.getMessage());
        }
    }

}
