package com.northcoders.record_shop.dto;

import org.hibernate.validator.constraints.URL;

public record AlbumImageUrlDTO(
        @URL(message = "Invalid image url")
        String imageUrl
) {
}
