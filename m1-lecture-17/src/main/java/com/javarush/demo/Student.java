package com.javarush.demo;

public class Student {

    private String name;
    private int design;
    private int math;
    private int english;

    public Student(String name, int design, int math, int english) {
        this.name = name;
        this.design = design;
        this.math = math;
        this.english = english;
    }

    public String getName() {
        return name;
    }

    public int getDesign() {
        return design;
    }

    public int getMath() {
        return math;
    }

    public int getEnglish() {
        return english;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", design=" + design +
                ", math=" + math +
                ", english=" + english +
                '}';
    }
}
