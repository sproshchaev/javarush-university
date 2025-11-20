package com.javarush;

import java.io.*;

public class InputStreamReaderDemo {

    public static void main(String[] args) throws IOException {
        String text = "Hello Java! Привет, Джава!";
        byte[] bytes = text.getBytes("UTF-8");

        // Цепочка: ByteArrayInputStream -> InputStreamReader
        try (InputStream byteStream = new ByteArrayInputStream(bytes)) {
            Reader reader = new InputStreamReader(byteStream, "UTF-8");
            int character;
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            } // Hello Java! Привет, Джава!
        }

        // Без InputStreamReader
        System.out.println("");
        try(InputStream byteStream = new ByteArrayInputStream(bytes)) {
            int byteDatd;
            while ((byteDatd = byteStream.read()) != -1) {
                System.out.print((char) byteDatd);
            } // Hello Java! ÐÑÐ¸Ð²ÐµÑ, ÐÐ¶Ð°Ð²Ð°!
        }



    }

}
