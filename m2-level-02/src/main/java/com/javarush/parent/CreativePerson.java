package com.javarush.parent;

// Ошибка Class cannot extend multiple classes
// public class CreativePerson extends Engineer, Musician {
// }

public  class CreativePerson implements IEngineer, IMusician {
    @Override
    public void work() {
        IEngineer.super.work(); // можем вызвать метод из IEngineer
        IMusician.super.work(); // может играть на гитаре
    }
}
