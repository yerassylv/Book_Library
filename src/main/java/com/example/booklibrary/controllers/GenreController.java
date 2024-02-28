package com.example.booklibrary.controllers;

import com.example.booklibrary.requests.GenreDeleteRequest;
import com.example.booklibrary.requests.GenreRequest;
import com.example.booklibrary.responses.GenreResponse;
import com.example.booklibrary.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/")
    public ResponseEntity<Collection<GenreResponse>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @PostMapping("/add")
    public ResponseEntity<GenreResponse> addGenre(final @RequestBody GenreRequest request) {
        try {
            return ResponseEntity.ok(genreService.addGenre(request));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new GenreResponse(null, null, e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public void deleteGenre(final @RequestBody GenreDeleteRequest request) {
        genreService.deleteGenre(request);
    }
}
