package com.javarush;

import org.atpfivt.ljv.LJV;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.util.Arrays.sort;

/**
 * JavaRush-University
 */
public class Main {

    public static void main(String[] args) {

        // сортировка строк двумерного массива
        int[][] matrix = {{3,2,1}, {4,-5,6}, {9,7,8}};


        for (int[] row : matrix) {
            Arrays.sort(row);
        }

        System.out.println("Отсортированные строки:");
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");;
            }
            System.out.println();;
        }

        // arrayToPrint(matrix);
        // System.out.println(Arrays.deepToString(matrix));
        // browse(new LJV(), matrix);


    }


    private static void arrayToPrint(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
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
