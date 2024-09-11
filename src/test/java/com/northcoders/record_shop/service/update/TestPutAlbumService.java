package com.northcoders.record_shop.service.update;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.dto.AlbumDetails;
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
public class TestPutAlbumService {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("Put an album")
    void putAlbumTest() throws Exception{
        Long id = 1L;
        Album preexistingAlbum = new Album(
                id,
                "Criss-Cross",
                1963,
                Genre.JAZZ,
                "Thelonious Monk",
                ""
        );

        AlbumDetails mockUpdateDetails = new AlbumDetails(
                "Journey Through The Secret Life Of Plants",
                1979,
                Genre.SOUNDTRACK,
                "Stevie Wonder",
                ""
        );

        Album mockUpdatedAlbum = new Album(
                id,
                mockUpdateDetails.name(),
                mockUpdateDetails.releaseYear(),
                mockUpdateDetails.genre(),
                mockUpdateDetails.artist(),
                mockUpdateDetails.imageUrl()
        );

        when(mockAlbumRepository.save(mockUpdatedAlbum)).thenReturn(mockUpdatedAlbum);
        mockAlbumRepository.save(preexistingAlbum);
        Album serviceResult = albumService.putAlbum(id, mockUpdateDetails);
        assertEquals(mockUpdatedAlbum, serviceResult);
    }
}
