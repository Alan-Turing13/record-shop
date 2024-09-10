package com.northcoders.record_shop.dto;

import com.northcoders.record_shop.model.Genre;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

public record AlbumDetails(

        @Pattern(regexp=".*[a-zA-Z].*", message = "Invalid album name")
        @NotBlank(message = "Album must have a name")
        String name,

        @Digits(integer=4, fraction = 0, message = "Invalid input for release year")
        @Range(min=1948, max=2025, message = "Invalid release year")
        int releaseYear,

        Genre genre,

        @Pattern(regexp=".*[a-zA-Z].*", message = "Invalid artist name")
        @NotBlank(message = "Artist name is blank")
        String artist,

        @URL(message = "Invalid image url")
        String imageUrl
) {
}
