package com.javarush;

import java.util.Objects;

public class Brand {

    // Поля (1)
    private String name;
    private City city;

    // Конструктор (2)
    public Brand(String name, City city) {
        this.name = name;
        this.city = city;
    }

    // Методы (3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    // (5) equals, hashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(name, brand.name) && Objects.equals(city, brand.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }

    // (4) toString
    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                "city='" + city + '\'' +
                '}';
    }
}
