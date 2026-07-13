package com.javarush.filmcache.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(schema = "sakila", name = "category")
@Data // TODO отрефакторить!
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    private String lastUpdate;

}
