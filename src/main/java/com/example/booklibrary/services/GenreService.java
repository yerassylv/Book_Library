package com.example.booklibrary.services;

import com.example.booklibrary.entities.Genre;
import com.example.booklibrary.repositories.GenreRepository;
import com.example.booklibrary.requests.GenreDeleteRequest;
import com.example.booklibrary.requests.GenreRequest;
import com.example.booklibrary.responses.GenreResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Collection<GenreResponse> getAllGenres() {
        return genreRepository.findAll().stream().map(GenreResponse::new).toList();
    }

    public GenreResponse addGenre(GenreRequest request) {
        if (genreRepository.getGenreByName(request.getName()).isPresent())
            throw new DataIntegrityViolationException("Genre already exists");
        Genre genre = Genre.builder()
                .name(request.getName())
                .build();
        return new GenreResponse(addGenre(genre));
    }

    public void deleteGenre(GenreDeleteRequest request) {
        Genre genre = genreRepository.getGenreByName(request.getName()).orElseThrow(EntityNotFoundException::new);
        genreRepository.delete(genre);
    }

    private Genre addGenre(Genre genre) {
        return save(genre);
    }

    private Genre save(Genre genre) {
        return genreRepository.save(genre);
    }
}
