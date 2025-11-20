package com.javarush;

import java.lang.ref.WeakReference;

public class ReferencesDemo {

    public static void main(String[] args) {

        // Strong reference - обычная ссылка
        String string = new String("Strong Reference");

        // Weak Reference - соберется быстро
        WeakReference<String> weak = new WeakReference<>(new String("Weak Reference"));

    }

}
