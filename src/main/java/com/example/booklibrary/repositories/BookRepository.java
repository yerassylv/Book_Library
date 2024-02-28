package com.example.booklibrary.repositories;

import com.example.booklibrary.entities.Book;
import com.example.booklibrary.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, Long> {
    Collection<Book> findBooksByGenresContains(Genre genre);
}
