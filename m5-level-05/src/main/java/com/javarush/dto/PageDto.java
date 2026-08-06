package com.javarush.dto;

import com.javarush.entity.User;

import java.util.List;

public record PageDto(List<User> content,
                      int pageNumber,
                      int totalPages,
                      long totalElements) {
}
