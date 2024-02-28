package com.example.booklibrary.responses;

import com.example.booklibrary.entities.Genre;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreResponse {
    private Long id;
    private String name;
    private String message;

    public GenreResponse(Genre genre) {
        setId(genre.getId());
        setName(genre.getName());
    }
}
