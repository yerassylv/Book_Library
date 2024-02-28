package com.example.booklibrary.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookEditRequest {
    private Long id;
    private String title;
    private String description;
    private String author;
    private int year;
    private int pageCount;
    private int availability;
    private int cost;
}
