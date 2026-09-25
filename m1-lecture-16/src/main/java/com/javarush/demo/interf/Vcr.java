package com.javarush.demo.interf;

public class Vcr implements Device {


    @Override
    public void on() {
        System.out.println("Видеомагнитофон включен");
    }

    @Override
    public void off() {
        System.out.println("Видеомагнитофон выключен");
    }
}
