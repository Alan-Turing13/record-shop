package com.northcoders.record_shop.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public record AlbumDetails(

        @NotBlank(message = "Album must have a name")
        String name,

        @Range(min=1948, max=2025)
        int releaseYear,

        @Range(min=0, max=20)
        int genre,

        @NotBlank(message = "Artist name is blank")
        String artist
) {
}
