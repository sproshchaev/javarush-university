package com.javarush;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {

    public static void main(String[] args) {

        Map<String, Integer> treeMap = new TreeMap();
        treeMap.put("B", 2);
        treeMap.put("A", 1);
        treeMap.put("C", 3);

        // Вывод - ключи отсортированы по алфавиту
        for (String name : treeMap.keySet()) {
            System.out.println(name + ":"  + treeMap.get(name));
        }

        // Класс Студент
        class Student {
            int math;
            int eng;
            int design;
            public Student(int math, int eng, int design) {
                this.math = math;
                this.eng = eng;
                this.design = design;
            }

            @Override
            public String toString() {
                return "Student [math=" + math + ", eng=" + eng + ", design=" + design + "]";
            }
        }

        // TreeMap с анонимным компаратором - сумму баллов
        Map<Student, String> studentStringTreeMap = new TreeMap<>(new  Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                int sum1 = student1.math + student1.eng + student1.design;
                int sum2 = student2.math + student2.eng + student2.design;
                return Integer.compare(sum1, sum2);
            }
        });

        studentStringTreeMap.put(new Student(1000,2000,3000),"Aнна");
        studentStringTreeMap.put(new Student(100,200,300),"Борис");

        for (Student student : studentStringTreeMap.keySet()) {
            System.out.println(studentStringTreeMap.get(student) + " баллы: "
                    + (student.math + student.eng + student.design));
        }

    }

}
