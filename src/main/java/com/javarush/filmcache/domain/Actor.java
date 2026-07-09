package com.javarush.filmcache.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(schema = "sakila", name = "actor")
@Data
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "last_update")
    private String lastUpdate;

}
