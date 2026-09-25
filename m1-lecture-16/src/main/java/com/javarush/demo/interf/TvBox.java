package com.javarush.demo.interf;

public class TvBox implements Device, Visible {

    @Override
    public void on() {
        System.out.println("ТВ включен");
    }

    @Override
    public void off() {
        System.out.println("ТВ выключен");
    }
}
