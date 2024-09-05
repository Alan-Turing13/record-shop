package com.northcoders.record_shop.service;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService implements AlbumServiceMethods{

    @Autowired
    AlbumRepository albumRepository;

    public List<Album> getAllAlbums() {
        Optional<List<Album>> albums = Optional.of(albumRepository.findAll());
        return albums.orElse(List.of()) ;
    }

    public Album getAlbumById(Long id) {
        return null;
    }

    public List<Album> getAlbumsByArtist(String artist) {
        return null;
    }

    public List<Album> getAlbumsByGenre(String genre) {
        return null;
    }

    public Album getAlbumByName(String name) {
        return null;
    }

    public Album postAlbum(Album album) {
        return null;
    }

    public List<Album> postMultipleAlbums(List<Album> albums) {
        return null;
    }

    public Album updateAlbum(Album album) {
        return null;
    }

    public void deleteAlbum(Long id) {

    }
}
