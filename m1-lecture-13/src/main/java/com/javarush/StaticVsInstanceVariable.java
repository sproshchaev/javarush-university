package com.javarush;

import java.util.Objects;

/**
 * Отличие статических и нестатических переменных
 */
public class StaticVsInstanceVariable {

    // Статическая переменная - принадлежит Классу!
    public static int totalStudents = 0;

    // Нестатическая переменная - принадлежит объекту!
    public String name;

    // Конструктор
    public StaticVsInstanceVariable(String studentName) {
        this.name = studentName;
        totalStudents++;
    }

    // Метод выводящий инфо о студенте
    public void showInfo() {
        System.out.println("Студент " + name + " | Общее число студентов: " + totalStudents);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StaticVsInstanceVariable that = (StaticVsInstanceVariable) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "StaticVsInstanceVariable{" +
                "name='" + name + '\'' +
                " totalStudents='" + totalStudents + '\'' +
                '}';
    }
}
