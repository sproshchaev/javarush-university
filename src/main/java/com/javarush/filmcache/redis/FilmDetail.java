package com.javarush.filmcache.redis;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FilmDetail {

    // id
    private Integer id;
    // title
    private String title;
    // description
    private String description;
    // releaseYear
    private Integer releaseYear;
    // rentalRate
    private BigDecimal rentalRate;
    // rating
    private BigDecimal rating;
    // actor
    private List<String> actors;
    // categories
    private List<String> categories;
}
