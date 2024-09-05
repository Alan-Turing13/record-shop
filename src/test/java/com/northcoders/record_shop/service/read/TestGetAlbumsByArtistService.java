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
public class TestGetAlbumsByArtistService {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("get all albums by artist")
    void getAlbumsByArtistTest() throws Exception{
        String artist = "Sergei Prokofiev";
        String name = "Piano Concertos Nos. 2 & 3";
        List<Album> mockAlbums = List.of(new Album(
                1L,
                name,
                1990,
                Genre.NEOCLASSICAL,
                artist
        ));

        when(mockAlbumRepository.findAllByArtist(artist)).thenReturn(mockAlbums);

        List<Album> serviceResult = albumService.getAlbumsByArtist(artist);

        assertIterableEquals(serviceResult, mockAlbums);

    }

}
