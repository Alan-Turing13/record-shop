package com.northcoders.record_shop.service;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.Genre;
import com.northcoders.record_shop.repository.AlbumRepository;
import com.northcoders.record_shop.service.AlbumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class TestGetAlbumByIdService {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("get album by id")
    void getAlbumByIdTest() throws Exception{
        Long id = 1L;
        Album mockAlbum = new Album(
                id,
                "The Real McCoy",
                1967,
                Genre.JAZZ,
                "McCoy Tyner"
        );

        when(mockAlbumRepository.findById(id)).thenReturn(Optional.of(mockAlbum));

        Album serviceResult = albumService.getAlbumById(id);

        assertEquals(serviceResult, mockAlbumRepository.findById(id).get());

    }

}
