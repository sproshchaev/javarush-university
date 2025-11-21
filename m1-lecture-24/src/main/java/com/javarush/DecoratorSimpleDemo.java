package com.javarush;

// Упрощенное демо Decorator pattern
public class DecoratorSimpleDemo {

    public static void main(String[] args) {
        Coffee coffee = new SimpleCofee();
        System.out.println(coffee.make()); // Кофе

        Coffee withMilk = new MilkDecorator(coffee);
        System.out.println(withMilk.make()); // Кофе + молоко

        Coffee withMilkAndSugar = new SugarDecorator(withMilk);
        System.out.println(withMilkAndSugar.make()); // Кофе + молоко + сахар

        //todo  Задание - добавить Сироп! :)

    }

    interface Coffee {
        String make();
    }

    static class SimpleCofee implements Coffee {

        @Override
        public String make() {
            return "Кофе";
        }
    }

    static class MilkDecorator implements Coffee {
        private Coffee coffee;

        public MilkDecorator(Coffee coffee) {
            this.coffee = coffee;
        }

        @Override
        public String make() {
            return coffee.make() + " + молоко";
        }
    }

    static class SugarDecorator implements Coffee {
        private Coffee coffee;

        public SugarDecorator(Coffee coffee) {
            this.coffee = coffee;
        }

        @Override
        public String make() {
            return coffee.make() + " + сахар";
        }
    }

}
