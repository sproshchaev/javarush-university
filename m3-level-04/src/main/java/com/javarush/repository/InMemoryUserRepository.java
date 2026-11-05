package com.javarush.repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Справочник покупателей в памяти: номер покупателя и имя.
 */
public class InMemoryUserRepository implements UserRepository {
    private final Map<Integer, String> names;

    public InMemoryUserRepository(Map<Integer, String> names) {
        this.names = new HashMap<>(names);
    }

    @Override
    public String findNameById(int id) {
        System.out.println("[DB] запрос покупателя " + id);
        return names.get(id);
    }
}
