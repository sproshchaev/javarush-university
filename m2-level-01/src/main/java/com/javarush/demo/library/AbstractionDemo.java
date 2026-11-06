package com.javarush.demo.library;

public class AbstractionDemo {

    public static void main(String[] args) {

        LibraryReader reader = new LibraryReader("Анна", 1024);
        reader.takeBook();
        reader.takeBook();
        System.out.println(reader.info());

        DeaneryStudent student = new DeaneryStudent("Анна", "ИВТ-21");
        student.addGrade(5);
        student.addGrade(4);
        System.out.println(student.info());
    }

}
