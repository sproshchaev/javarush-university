package com.javarush.technics;

abstract class Printer {

    protected abstract void printDoc();

    // Возвращаемый тип - Object
    public abstract Object getConfiguration();

    public abstract String getType();

}
