package com.javarush.controller;

import com.javarush.model.User;
import com.javarush.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    // @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/users")
    public List<User> getUser() {

        userService.registerUser("Alice");
        userService.registerUser("Bob");

        return userService.getAllUsers();
    }

}
