package com.javarush;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class SimpleBufferedReaderDemo {

    public static void main(String[] args) throws IOException {

        String text = "Первая строка\nВторая строка\nТретья строка"; // null
        BufferedReader reader = new BufferedReader(new StringReader(text));
        // Чтение построчно
        String line;
        while((line = reader.readLine()) != null)  {
            System.out.println(line);
        }
        reader.close();

    }

}
