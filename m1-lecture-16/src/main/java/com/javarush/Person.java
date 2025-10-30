package com.javarush;

import java.util.Objects;

public class Person implements Comparable<Person> {

    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    // public interface Comparable<T> {
    //    int compareTo(T other)
    // }
    // Если результат < 0 - текущий объект (this) меньше
    // Если результат  = 0 - объекты равны по порядку (необязательно по equals)
    // Если результат > 0 - то текущий объект (this) больше
//    @Override
//    public int compareTo(Person o) {
//        int cmp = Integer.compare(this.age, o.age);
//        // return cmp != 0 ? cmp : this.firstName.compareTo(o.firstName);
//        if (cmp != 0) {
//            return cmp;
//        } else  {
//            return this.firstName.compareTo(o.firstName);
//        }
//    }

    // Сортировка только по возрасту
//    @Override
//    public int compareTo(Person o) {
//        // return Integer.compare(age, o.age);
//        if (this.age < o.age) {
//             return -1; // Если результат < 0 - текущий объект (this) меньше
//        } else if (this.age > o.age) {
//            return 1; // Если результат > 0 - то текущий объект (this) больше
//        } else {
//            return 0; // Если результат = 0 - объекты равны по порядку
//        }
//    }

    // int result = Person1.compareTo(Person2)
    // < 0 => Person1  Person2
    // > 0 => Person2  Person1
    // = 0 => Person1  Person2
    @Override
    public int compareTo(Person o) {
        if (this.age < o.age) {
            return -1; // Если результат < 0 - текущий объект (this) меньше. Стоит левее
        };
         if (this.age > o.age) {
             return 1; // Person1 Строит правее Person2
         }
        return 0; // Равны
    }

}
