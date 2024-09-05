package com.northcoders.record_shop.service;

import com.northcoders.record_shop.model.Album;

import java.util.List;

public interface AlbumServiceMethods {

    List<Album> getAllAlbums();
    Album getAlbumById(Long id);
    List<Album> getAlbumsByArtist(String artist);
    List<Album> getAlbumsByGenre(String genre);
    Album getAlbumByName(String name);

    Album postAlbum(Album album);
    List<Album> postMultipleAlbums(List<Album> albums);

    Album updateAlbum(Album album);

    void deleteAlbum(Long id);

}
