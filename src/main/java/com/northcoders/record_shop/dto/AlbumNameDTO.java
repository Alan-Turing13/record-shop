package com.northcoders.record_shop.dto;

import jakarta.validation.constraints.NotBlank;

public record AlbumNameDTO(
        @NotBlank(message = "No album name was provided")
        String name
) {
}
