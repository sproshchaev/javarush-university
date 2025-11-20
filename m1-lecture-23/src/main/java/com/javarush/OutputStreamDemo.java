package com.javarush;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class OutputStreamDemo {

    public static void main(String[] args) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            //
            outputStream.write(65); // "A"
            outputStream.write(66); // "B"

            //
            byte[] data = " Hello, ".getBytes();
            outputStream.write(data);

            byte[] fullData = " World!".getBytes();
            outputStream.write(fullData, 1, 6); //

            outputStream.flush();

            byte[] result = outputStream.toByteArray();
            System.out.println("Результат: " + new String(result)); // Результат: AB Hello, World!

        }
    }
}
