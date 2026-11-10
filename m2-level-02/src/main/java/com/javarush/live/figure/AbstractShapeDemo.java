package com.javarush.live.figure;

import java.util.ArrayList;
import java.util.List;

public class AbstractShapeDemo {

    public static void main(String[] args) {

        int[] someArrayInt = {1, 2, 3};
        BoxFigure[] boxFigures = {new BoxFigure(1, 2), new BoxFigure(3, 4)};
        List<BoxFigure> boxFigureList = new ArrayList<>();
        boxFigureList.add(new BoxFigure(5, 6));
        boxFigureList.add(new BoxFigure(7, 8));

        String someString = new String("Фигура");
        Figure[] figures = {new RoundFigure(2), new BoxFigure(3, 4), /*someString*/ };

        // Нельзя создать объект абстрактного класса!
        // Figure someFigure = new Figure();

        for (Figure figure : figures) {
            System.out.println("Фигура: " + figure);
            System.out.println(figure.describe());
        }

        System.out.println("Фигура: " + figures[0]);
        System.out.println("Фигура: " + figures[1]);

    }

}
