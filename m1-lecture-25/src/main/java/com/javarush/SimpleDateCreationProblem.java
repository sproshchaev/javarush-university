package com.javarush;

import java.util.Date;

public class SimpleDateCreationProblem {

    public static void main(String[] args) {
        Date christmas2025 = new Date(125, 11, 25); // 25.12.2025
        System.out.println("Дата, которую мы хотим: " + christmas2025);

        // 01.01.2025
        Date newYear2025 = new Date(125, 0, 1);
        System.out.println("01.01.2025 " + newYear2025); // 01.01.2025 Wed Jan 01 00:00:00 YEKT 2025

        Date date = new Date(-125, 0, 1);
        System.out.println(date); // Sun Jan 01 00:00:00 YEKT 1775

    }

}
