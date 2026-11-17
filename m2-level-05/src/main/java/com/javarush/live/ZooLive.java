package com.javarush.live;

public class ZooLive {

    // Без использования Дженериков (обобщений) - тип String
    static class ZooString {
        private String animal;
        void set(String animal) {
            this.animal = animal;
        }
        String get() {
            return  animal;
        }
    }

    // Без использования Дженериков (обобщений) - тип Integer
    static class ZooInteger {
        private Integer animal;
        void set(Integer animal) {
            this.animal = animal;
        }
        Integer get() {
            return  animal;
        }
    }

    // Параметризированный статический класс
    static class Zoo<T> {
        // Состояние класса
        private T animal;
        // Сеттер
        void set(T animal) {
            this.animal = animal;
        }
        // Геттер
        T get() {
            return  animal;
        }
    }

    public static void main(String[] args) {

        ZooString zooString = new ZooString();
        ZooInteger zooInteger = new ZooInteger();

        Zoo<String> zooStrGen = new Zoo<>();
        Zoo<Integer> zooIntGen = new Zoo<>();

        zooStrGen.set("Тигр");
        System.out.println(zooStrGen.get());

    }

}
