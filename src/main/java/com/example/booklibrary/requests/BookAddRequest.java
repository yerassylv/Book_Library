package com.example.booklibrary.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookAddRequest {
    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String author;

    @NotNull
    @Range(min = 1)
    private int year;

    @NotNull
    @Range(min = 1)
    private int pageCount;

    @NotNull
    @Range(min = 1)
    private int availability;

    @NotNull
    @Range(min = 1)
    private int cost;
}
