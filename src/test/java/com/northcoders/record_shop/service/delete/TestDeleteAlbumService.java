package com.northcoders.record_shop.service.delete;

import com.northcoders.record_shop.repository.AlbumRepository;
import com.northcoders.record_shop.service.AlbumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.mockito.Mockito.*;

@DataJpaTest
public class TestDeleteAlbumService {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("Delete an album")
    void deleteAlbumTest() throws Exception{
        Long id = 1L;
        when(mockAlbumRepository.existsById(id)).thenReturn(true);
        albumService.deleteAlbum(id);
        verify(mockAlbumRepository, times(1)).deleteById(id);
    }
}

