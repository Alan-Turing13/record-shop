package com.northcoders.record_shop.service.read;

import com.northcoders.record_shop.exception.NotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
public class TestGetAlbumsByYearService {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("get all albums by year")
    void getAlbumsByArtistTest() throws Exception{
        int year = 1990;

        List<Album> mockAlbums = List.of(new Album(
                1L,
                "Piano Concertos Nos. 2 & 3",
                year,
                Genre.NEOCLASSICAL,
                "Sergei Prokofiev"
        ));

        when(mockAlbumRepository.findAllByReleaseYear(year)).thenReturn(mockAlbums);

        List<Album> serviceResult = albumService.getAlbumsByYear(year);

        assertIterableEquals(serviceResult, mockAlbums);

    }

    @Test
    @DisplayName("no albums found for year")
    void noAlbumsForYearTest() throws Exception{

        List<Album> mockAlbums = List.of(new Album(
                1L,
                "Piano Concertos Nos. 2 & 3",
                1990,
                Genre.NEOCLASSICAL,
                "Sergei Prokofiev"
        ));

        when(mockAlbumRepository.findAllByReleaseYear(1990)).thenReturn(mockAlbums);

        assertThrows(NotFoundException.class, () -> albumService.getAlbumsByYear(2024));
    }

}