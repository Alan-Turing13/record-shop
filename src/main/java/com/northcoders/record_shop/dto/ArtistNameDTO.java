package com.northcoders.record_shop.dto;

import jakarta.validation.constraints.NotBlank;

public record ArtistNameDTO (
        @NotBlank(message = "Artist name is blank")
        String artistName
){
}
