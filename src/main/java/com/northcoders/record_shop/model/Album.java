package com.northcoders.record_shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

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

    @NotBlank(message = "Album must have a name")
    @Column(nullable = false)
    private String name;

    @Range(min=1948, max=2025)
    @Column(name = "release-year")
    private int releaseYear;

    @Column
    private Genre genre;

    @NotBlank(message = "Artist name is blank")
    @Column(nullable = false)
    private String artist;

    @URL(message = "invalid image url")
    @Column(name = "image-url")
    private String imageUrl;

    public Album(long id, String name, int releaseYear, Genre genre, String artist) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.artist = artist;
    }

    public Album(String name, int releaseYear, Genre genre, String artist) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.artist = artist;
    }
}
