package com.javarush.live;

import java.util.ArrayList;
import java.util.List;

public class SuperLive {

    static class Warrior {
        @Override
        public String toString() {
            return "Воин";
        }
    }

    static class MagicWarrior extends Warrior {
        @Override
        public String toString() {
            return "Воин-маг";
        }
    }

    static void fill(List<? super Warrior> warriors) {
        warriors.add(new Warrior());
        warriors.add(new MagicWarrior());
        Object first = warriors.get(0);
        System.out.println(first);
    }

    public static void main(String[] args) {
        List<Object> values = new ArrayList<>();
        fill(values);
        System.out.println(values);
    }

}
