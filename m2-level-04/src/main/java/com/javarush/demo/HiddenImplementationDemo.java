package com.javarush.demo;

public class HiddenImplementationDemo {
    public static void main(String[] args) {
        OrderService service = new OrderService(new ConsoleStorage());
        service.placeOrder("Заказ 1");

        // Подменяем объект прямо во время работы программы
        MemoryStorage memory = new MemoryStorage();
        service.setStorage(memory);
        service.placeOrder("Заказ 2");
        service.placeOrder("Заказ 3");
        System.out.println("В памяти заказов: " + memory.count());
    }
}

interface Storage {
    void save(String item);
}

class ConsoleStorage implements Storage {
    @Override
    public void save(String item) {
        System.out.println("Печатаю в консоль: " + item);
    }
}

class MemoryStorage implements Storage {
    private int count;

    @Override
    public void save(String item) {
        count++;
    }

    int count() {
        return count;
    }
}

// Сервис знает только интерфейс Storage
class OrderService {
    private Storage storage;

    OrderService(Storage storage) {
        this.storage = storage;
    }

    void setStorage(Storage storage) {
        this.storage = storage;
    }

    void placeOrder(String order) {
        storage.save(order);
    }
}
