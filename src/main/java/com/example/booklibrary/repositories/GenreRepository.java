package com.example.booklibrary.repositories;

import com.example.booklibrary.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> getGenreByName(String name);
}
