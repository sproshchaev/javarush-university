package com.javarush.live;


import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

// [B] -> 0101010101001110001 -> [B]
public class Book implements Serializable, Closeable {

    @Override
    public void close() throws IOException {

    }
}
