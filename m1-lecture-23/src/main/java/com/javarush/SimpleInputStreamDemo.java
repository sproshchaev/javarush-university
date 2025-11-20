package com.javarush;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimpleInputStreamDemo {

    public static void main(String[] args) throws IOException {
        // Простое демо InputStream
        byte[] data = "Hello Java!".getBytes();  // "Hello Java!" => ...0101...010101...(.read()=-1)

        InputStream in = new ByteArrayInputStream(data);

        // Читать по одному байту
        int byteData;
        while ((byteData = in.read()) != -1) {
            System.out.println((char) byteData);
        }

    }

}
