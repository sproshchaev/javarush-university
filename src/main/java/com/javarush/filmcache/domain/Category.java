package com.javarush.filmcache.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "sakila", name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
