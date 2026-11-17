package com.javarush.live.warrior;

import com.javarush.live.warrior.*;

import java.util.ArrayList;
import java.util.List;

public class WarriorDemo {

    public static void main(String[] args) {

        List<Warrior> warriors = new ArrayList<>();
        warriors.add(new Warrior());
        warriors.add(new MagicWarrior());

        WarriorDemo wd = new WarriorDemo();
        wd.print(warriors);

    }

    public void print(List<? extends Warrior> warriors) {
        for (Warrior warrior : warriors) {
            System.out.println(warrior.name());
        }
        // warriors.add(new Warrior());
    }



}
