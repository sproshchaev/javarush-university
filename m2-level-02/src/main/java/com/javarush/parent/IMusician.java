package com.javarush.parent;

public interface IMusician {
    default void work() {
        System.out.println("Играю на гитаре");
    }
}
