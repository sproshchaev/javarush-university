package com.javarush.dto;

import java.util.List;

public record AuthorDto(Long id, String name, List<String> books) {
}
