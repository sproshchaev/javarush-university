package com.javarush.live;

import java.util.List;

public class ExtendsLive {

    static class Warrior {
        String name() {
            return "Воин";
        }
    }

    static class MagicWarrior extends Warrior {
        @Override
        String name() {
            return "Воин-маг";
        }
    }
    //                  < любой наследник Warrior >
    static void print(List<? extends Warrior> warriors) {
        for (Warrior warrior : warriors) {
            System.out.println(warrior.name());
        }
        // warriors.add(new Warrior());
    }

    public static void main(String[] args) {
        List<MagicWarrior> warriors = List.of(new MagicWarrior());
        print(warriors);
    }

}
