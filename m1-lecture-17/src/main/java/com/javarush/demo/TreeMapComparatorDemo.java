package com.javarush.demo;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapComparatorDemo {

    public static void main(String[] args) {

                                  // Студент Допуск
        var students = new TreeMap<Student, Boolean>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int gradeFirst = o1.getDesign() + o1.getEnglish() + o1.getMath();
                int gradeSecond = o2.getDesign() + o2.getEnglish() + o2.getMath();

                // -1, 1, 0
                if (gradeFirst > gradeSecond) {
                    return -1;
                } else {
                    if (gradeFirst == gradeSecond) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        });

        Student student1 = new Student("Иванов", 4, 5, 5);
        students.put(student1, true);

        students.put(new Student("Петров", 5, 5, 5), true);
        students.put(new Student("Сидоров", 4, 5, 5), false);
        students.put(new Student("Кузьмин", 3, 5, 5), true);

        for (Map.Entry<Student, Boolean> pair : students.entrySet()) {
            System.out.println(pair.getKey() + " - допущен: " + pair.getValue());
        }
        System.out.println("Размер " + students.size());

    }


}
