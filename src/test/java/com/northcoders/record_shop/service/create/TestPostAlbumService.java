package com.northcoders.record_shop.service.create;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.Genre;
import com.northcoders.record_shop.repository.AlbumRepository;
import com.northcoders.record_shop.service.AlbumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class TestPostAlbumService{

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("Post an album")
    void postAlbumTest() throws Exception{
        Album mockAlbum = new Album(
                1L,
                "Criss-Cross",
                1963,
                Genre.JAZZ,
                "Thelonious Monk"
        );
        when(mockAlbumRepository.save(mockAlbum)).thenReturn(mockAlbum);
        Album serviceResult = albumService.postAlbum(mockAlbum);
        assertEquals(mockAlbum, serviceResult);
    }
}