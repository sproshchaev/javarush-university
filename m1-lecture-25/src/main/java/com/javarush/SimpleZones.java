package com.javarush;

import java.time.ZoneId;
import java.util.Set;

public class SimpleZones {

    public static void main(String[] args) {
        Set<String> zones = ZoneId.getAvailableZoneIds();
        System.out.println("Всего зон: " + zones.size()); // 604

        System.out.println("America/New_York: " + ZoneId.of("America/New_York"));
        ZoneId pltId = ZoneId.of("America/Los_Angeles");
        System.out.println(pltId);

    }

}
