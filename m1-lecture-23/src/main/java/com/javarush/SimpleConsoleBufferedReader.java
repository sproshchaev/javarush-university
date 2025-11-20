package com.javarush;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleConsoleBufferedReader {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите сообщение:");
        String message = reader.readLine();
        System.out.println("вы ввели: " + message);
        reader.close();
    }

}
