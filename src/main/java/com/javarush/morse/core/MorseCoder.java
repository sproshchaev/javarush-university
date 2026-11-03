package com.javarush.morse.core;

/**
 * Ядро программы - два метода, каждый работает с одной строкой текста
 */
public class MorseCoder {

    /***
     * Идет по символам и ставит пробел перед каждым кодом, кроме первого (i > 0)
     * @param line
     * @return
     */
    public String encode(String line) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (i > 0) {
                result.append(' ');
            }
            result.append(Alphabet.toMorse(line.charAt(i)));
        }
        return result.toString();
    }

    // ".- -... .--"
    public String decode(String line) {
        // TODO: разбить строку на коды, каждый код заменить символом
        StringBuilder result = new StringBuilder();
        for (String code : line.trim().split(" +")) { // "А Б С" -> "А Б С".split(" ") => "А" "Б" "С" (массив строк)
            if (!code.isEmpty()) { // !true => false
               result.append(Alphabet.fromMorse(code));
            }
        }
        return result.toString();
    }

}
