package com.javarush;

public class MyResource implements AutoCloseable{
    @Override
    public void close() throws Exception {
        System.out.println("Closing resource");
    }
}
