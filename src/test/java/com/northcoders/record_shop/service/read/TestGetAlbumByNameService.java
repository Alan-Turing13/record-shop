package com.northcoders.record_shop.service.read;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.Genre;
import com.northcoders.record_shop.repository.AlbumRepository;
import com.northcoders.record_shop.service.AlbumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class TestGetAlbumByNameService {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("get album by name")
    void getAlbumByNameTest() throws Exception{
        Long id = 1L;
        String albumName = "The Real McCoy";

        Album mockAlbum = new Album(
                id,
                albumName,
                1967,
                Genre.JAZZ,
                "McCoy Tyner"
        );

        when(mockAlbumRepository.findByName(albumName)).thenReturn(mockAlbum);

        Album serviceResult = albumService.getAlbumByName(albumName);

        assertEquals(serviceResult, mockAlbumRepository.findByName(albumName));

    }

}