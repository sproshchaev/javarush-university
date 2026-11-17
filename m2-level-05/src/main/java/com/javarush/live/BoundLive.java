package com.javarush.live;

public class BoundLive {

    // Родительский класс
    static class Cat {
        String voice() {
            return "Мяу";
        }
    }
    // Класс наследника
    static class Tiger extends Cat { }

    // Не класс наследник Cat
    static class Dog { }

    static class Zoo<T extends Cat> {
        // Состояние
        private final T cat;
        // Конструктор
        Zoo(T cat) {
            this.cat = cat;
        }
        // Метод параметризированного класса
        String voice() {
            return cat.voice();
        }
    }

    public static void main(String[] args) {
        Zoo<Tiger> zoo = new Zoo<>(new Tiger());
        System.out.println(zoo.voice()); // Мяу

        // Type parameter 'Dog' is not within its bound; should extend 'Cat'
        // Zoo<Dog> zooDog = new Zoo<>(new Dog());
    }

}
