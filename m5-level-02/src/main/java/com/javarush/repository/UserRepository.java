package com.javarush.repository;

import com.javarush.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
}
