package com.javarush.filmcache.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(schema = "sakila", name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long id;

    private String title;

    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "rental_rate")
    private BigDecimal rentaleRate;

    private BigDecimal rating;

    @ManyToMany
    @JoinTable(
            schema = "sakila",
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;

    @ManyToMany
    @JoinTable(
            schema = "sakila",
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

}
