package com.northcoders.record_shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "release-year")
    private int releaseYear;

    @Column
    private Genre genre;

    @Column
    private String artist;

    public Album(String name, int releaseYear, Genre genre, String artist) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.artist = artist;
    }
}
