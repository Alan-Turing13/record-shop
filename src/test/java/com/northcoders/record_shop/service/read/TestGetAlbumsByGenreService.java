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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class TestGetAlbumsByGenreService {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("get all albums by genre")
    void getAlbumsByGenreTest() throws Exception{

        List<Album> mockAlbums = List.of(new Album(
                1L,
                "Piano Concertos Nos. 2 & 3",
                1990,
                Genre.NEOCLASSICAL,
                "Sergei Prokofiev"
        ));

        when(mockAlbumRepository.findAllByGenre(6)).thenReturn(mockAlbums);
        List<Album> serviceResult = albumService.getAlbumsByGenre("NEOCLASSICAL");
        assertIterableEquals(serviceResult, mockAlbums);
    }

}
