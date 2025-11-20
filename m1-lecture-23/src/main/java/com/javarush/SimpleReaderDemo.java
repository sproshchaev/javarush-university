package com.javarush;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

public class SimpleReaderDemo {
    public static void main(String[] args) throws IOException {
        String text = "Hello Java!"; // или считать с консоли
        Reader reader = new StringReader(text);

        int symbol;
        while ((symbol = reader.read()) != -1) {
            System.out.print((char) symbol);
        }
        reader.close();

    }
}
