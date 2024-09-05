package com.northcoders.record_shop;

import com.northcoders.record_shop.repository.AlbumRepository;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TestService {

    @Mock
    private AlbumRepository mockAlbumRepository;

}
