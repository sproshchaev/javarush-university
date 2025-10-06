package com.javarush;

import org.atpfivt.ljv.LJV;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;

/**
 * JavaRush-University
 */
public class Main {

    public static void main(String[] args) {

        // стек:       int, boolean, float, ... = 0, false, 0.0 ...
        // куча (хип): Integer, Boolean, int[], String = null

        String[] source = {"Один", "Два", "Три"}; // Объект
        String[] target = source;

        System.out.println(Arrays.toString(target));



        // browse(new LJV(), boolArray);

    }



























    public static void browse(LJV ljv, Object obj) {
        try {
            var dot = URLEncoder.encode(ljv.drawGraph(obj), "UTF8")
                    .replaceAll("\\+", "%20");
            Desktop.getDesktop().browse(
                    new URI("https://dreampuf.github.io/GraphvizOnline/#"
                            + dot));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
