package com.example.booklibrary.controllers;

import com.example.booklibrary.requests.BookEditRequest;
import com.example.booklibrary.requests.BookAddRequest;
import com.example.booklibrary.responses.BookResponse;
import com.example.booklibrary.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public ResponseEntity<Collection<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<Collection<BookResponse>> getBooksByGenre(
            final @PathVariable String genre
    ) {
        return ResponseEntity.ok(bookService.getAllBooksByGenre(genre));
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBook(final @RequestBody @Valid BookAddRequest request) {
        return ResponseEntity.ok(bookService.addBook(request));
    }

    @PostMapping("/edit")
    public ResponseEntity<BookResponse> editBook(
            final @RequestBody BookEditRequest request
    ) {
        return ResponseEntity.ok(new BookResponse(bookService.editBook(request)));
    }

    @PostMapping("/edit/{id}/add-genre/{genre}")
    public ResponseEntity<BookResponse> addGenre(
            final @PathVariable Long id,
            final @PathVariable String genre
    ) {
        return ResponseEntity.ok(new BookResponse(bookService.addGenreToBook(id, genre)));
    }


    @GetMapping("/remove/{id}")
    public ResponseEntity<Boolean> removeBook(final @PathVariable Long id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }
}
