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

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class TestGetAllService {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("get all albums")
    void getAllAlbumsTest() throws Exception{
        List<Album> mockAlbums = List.of(new Album(
                1L,
                "Criss-Cross",
                1963,
                Genre.JAZZ,
                "Thelonious Monk"
        ));

        when(mockAlbumRepository.findAll()).thenReturn(mockAlbums);

        List<Album> serviceResult = albumService.getAllAlbums();

        assertIterableEquals(serviceResult, mockAlbums);

    }

}
