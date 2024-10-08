package com.northcoders.record_shop.repository;

import com.northcoders.record_shop.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findAllByArtist(String artist);
    List<Album> findAllByReleaseYear(Integer year);
    List<Album> findAllByGenre(Integer genre);
    Album findByName(String name);
    boolean existsByName(String name);
}
