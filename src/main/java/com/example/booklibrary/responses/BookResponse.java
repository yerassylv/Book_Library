package com.example.booklibrary.responses;

import com.example.booklibrary.entities.Book;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String description;
    private String author;
    private int year;
    private int pageCount;
    private int availability;
    private int cost;
    private Collection<GenreResponse> genres;

    public BookResponse(Book book) {
        setId(book.getId());
        setTitle(book.getTitle());
        setDescription(book.getDescription());
        setAuthor(book.getAuthor());
        setYear(book.getYear());
        setPageCount(book.getPageCount());
        setAvailability(book.getAvailability());
        setCost(book.getCost());
        setGenres(
                Optional.ofNullable(book.getGenres())
                        .orElse(Collections.emptyList())
                        .stream().map(GenreResponse::new)
                        .toList()
        );
    }
}
