package com.example.booklibrary.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "books")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String author;
    private int year;
    private int pageCount;

    private int availability;
    private int cost;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Collection<Genre> genres;

    @OneToMany(mappedBy = "book")
    private Collection<Order> orders;
}
