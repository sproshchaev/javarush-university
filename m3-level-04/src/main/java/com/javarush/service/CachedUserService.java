package com.javarush.service;

import com.javarush.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class CachedUserService {
    private final UserRepository repository;
    private final Map<Integer, String> cache = new HashMap<>();

    public CachedUserService(UserRepository repository) {
        this.repository = repository;
    }

    public String getName(int id) {
        // containsKey запоминает и ответ null: пользователя нет, повторно в базу не идем
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        String name = repository.findNameById(id);
        cache.put(id, name);
        return name;
    }
}
