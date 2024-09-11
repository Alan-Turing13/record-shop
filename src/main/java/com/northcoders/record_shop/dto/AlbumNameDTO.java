package com.northcoders.record_shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AlbumNameDTO(
        @Pattern(regexp=".*[a-zA-Z].*", message = "Invalid album name")
        @NotBlank(message = "No album name was provided")
        String name
) {
}
