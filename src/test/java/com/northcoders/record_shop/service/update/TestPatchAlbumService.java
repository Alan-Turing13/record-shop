package com.northcoders.record_shop.service.update;

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
public class TestPatchAlbumService {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("Patch an album")
    void patchAlbumTest() throws Exception{
        Long id = 1L;
        String mockUrl = "blah";

        Album preexistingAlbum = new Album(
                id,
                "Criss-Cross",
                1963,
                Genre.JAZZ,
                "Thelonious Monk"
        );

        Album mockUpdatedAlbum = new Album(
                id,
                preexistingAlbum.getName(),
                preexistingAlbum.getReleaseYear(),
                preexistingAlbum.getGenre(),
                preexistingAlbum.getArtist(),
                mockUrl
        );

        when(mockAlbumRepository.save(mockUpdatedAlbum)).thenReturn(mockUpdatedAlbum);
        mockAlbumRepository.save(preexistingAlbum);
        Album serviceResult = albumService.updateAlbumArtwork(mockUpdatedAlbum);
        assertEquals(mockUpdatedAlbum, serviceResult);
    }
}
