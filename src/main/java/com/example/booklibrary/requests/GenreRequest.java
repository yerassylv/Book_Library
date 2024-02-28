package com.example.booklibrary.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreRequest {
    private Long id;
    private String name;
}
