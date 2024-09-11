package com.northcoders.record_shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ArtistNameDTO (
        @Pattern(regexp=".*[a-zA-Z].*", message = "Invalid album name")
        @NotBlank(message = "Artist name is blank")
        String artistName
){
}
