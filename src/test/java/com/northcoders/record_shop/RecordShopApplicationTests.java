package com.northcoders.record_shop;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecordShopApplicationTests {

	@Test
	void contextLoads() {
		Album album1 = Album.builder()
				.name("Criss-Cross")
				.releaseYear(1963)
				.artist("Thelonious Monk")
				.genre(Genre.JAZZ)
				.build();

		Album album2 = Album.builder()
				.name("The Real McCoy")
				.releaseYear(1967)
				.artist("McCoy Tyner")
				.genre(Genre.JAZZ)
				.build();

		System.out.println("Album 1 id = " + album1.getId());
		System.out.println("Album 2 id = " + album2.getId());
	}


}
