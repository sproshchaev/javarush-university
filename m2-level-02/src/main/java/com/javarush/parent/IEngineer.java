package com.javarush.parent;

public interface IEngineer {
    default void work() {
        System.out.println("Пишу код...");
    }
}
