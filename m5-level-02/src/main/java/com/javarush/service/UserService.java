package com.javarush.service;

import com.javarush.model.User;
import com.javarush.repository.InMemoryUserRepository;
import com.javarush.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * Пример внедрения зависимостей через конструктор
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    // @Autowired
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @PostConstruct
    private void init() {
        System.out.println("Метод был вызван через @PostConstruct");
    }

    @PreDestroy
    private void close() {
        System.out.println("Метод вызывается при завершении приложения через @PreDestroy");
    }

// Для Legacy
//    public UserService() {
//        // Жесткая связь
//        this.userRepository = new InMemoryUserRepository();
//    }

    public User registerUser(String name) {
        User user = new User(null, name);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
