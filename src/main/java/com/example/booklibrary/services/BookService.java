package com.example.booklibrary.services;

import com.example.booklibrary.entities.Book;
import com.example.booklibrary.entities.Genre;
import com.example.booklibrary.repositories.BookRepository;
import com.example.booklibrary.repositories.GenreRepository;
import com.example.booklibrary.requests.BookEditRequest;
import com.example.booklibrary.requests.BookAddRequest;
import com.example.booklibrary.responses.BookResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public Collection<BookResponse> getAllBooks() {
        Collection<BookResponse> bookResponses = new ArrayList<>();
        for (Book book : getAll()) {
            bookResponses.add(new BookResponse(book));
        }
        return bookResponses;
    }

    public Collection<BookResponse> getAllBooksByGenre(String name) {
        Genre genre = genreRepository.getGenreByName(name).orElseThrow();
        return getBookByGenre(genre).stream().map(BookResponse::new).toList();
    }

    public Collection<Book> getAll() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Collection<Book> getBookByGenre(Genre genre) {
        return genre.getBooks();
    }

    public BookResponse addBook(BookAddRequest request) {
        Book book = new Book();
        BeanUtils.copyProperties(request, book);
        return new BookResponse(save(book));

    }

    public Book editBook(BookEditRequest request) {
        Book book = bookRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(request, book);
        return save(book);
    }

    public Book addGenreToBook(Long id, String name) {
        Book book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Genre genre = genreRepository.getGenreByName(name).orElseThrow(EntityNotFoundException::new);
        return addGenreToBook(book, genre);
    }

    public Book addGenreToBook(Book book, Genre genre) {
        Collection<Genre> genres = book.getGenres();
        genres.add(genre);
        book.setGenres(genres);
        genre.getBooks().add(book);
        genreRepository.save(genre);
        return save(book);
    }

    public boolean deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.filter(this::deleteBook).isPresent();
    }

    public boolean deleteBook(Book book) {
        bookRepository.delete(book);
        return true;
    }

    private Book save(Book book) {
        return bookRepository.save(book);
    }
}
