package com.javarush.demo;

import java.lang.Integer;
import java.util.ArrayList;

public class VarAndDiamondDemo {

    public static void main(String[] args) {

        var list = new ArrayList<>();
        list.add(Integer.parseInt("1"));
        list.add(2);

        System.out.println(list);

    }

}
