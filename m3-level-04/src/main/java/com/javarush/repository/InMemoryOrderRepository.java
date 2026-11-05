package com.javarush.repository;

import com.javarush.model.Order;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Хранилище заказов в памяти вместо настоящей базы данных.
 */
public class InMemoryOrderRepository implements DatabaseRepository {
    private final Map<String, Order> orders = new LinkedHashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public void delete(String orderId) {
        orders.remove(orderId);
    }

    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    public int count() {
        return orders.size();
    }
}
