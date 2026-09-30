package demo;

import org.atpfivt.ljv.LJV;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HashCollisionDemo {

    public static void main(String[] args) {

        int[] array = {10, 20, 30};

        // Коллизия
        System.out.println("\"Aa\".hashCode = " + "Aa".hashCode());
        System.out.println("\"BB\".hashCode = " + "BB".hashCode());

        System.out.println("Aa".equals("BB"));

        Map<String, Integer> map = new HashMap<>();

        map.put("Aa", 1);
        map.put("BB", 2);
        map.put("Кофе", 3);

        browse(map);

    }














    // Рисует внутреннее устройство объекта и открывает картинку в браузере
    static void browse(Object obj) {
        LJV ljv = new LJV()
                .setTreatAsPrimitive(String.class)
                .setTreatAsPrimitive(Integer.class);
        try {
            String dot = URLEncoder.encode(ljv.drawGraph(obj), "UTF-8").replaceAll("\\+", "%20");
            Desktop.getDesktop().browse(new URI("https://dreampuf.github.io/GraphvizOnline/#" + dot));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
