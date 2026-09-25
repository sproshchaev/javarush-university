package com.javarush.demo.interf;

public class Main {

    public static void main(String[] args) {

        // Слева интерфейс - справа реализация!

        Device tvBox = new TvBox();

        tvBox.on(); // ТВ включен
        tvBox.off(); // ТВ выключен

        Device vcr = new Vcr();

        vcr.on(); // Видеомагнитофон включен
        vcr.off(); // Видеомагнитофон выключен

    }

}
