package com.javarush.demo.library;

public class DeaneryStudent {

    private String name;
    private String group;
    private int gradesSum;
    private int gradesCount;

    public DeaneryStudent(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public void addGrade(int grade) {
        gradesSum += grade;
        gradesCount++;
    }

    public double averageGrade() {
        return (double) gradesSum / gradesCount;
    }

    public String info() {
        return "Студент " + name + ", группа " + group + ", средний балл: " + averageGrade();
    }

}
