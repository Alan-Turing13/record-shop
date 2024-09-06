package com.northcoders.record_shop.service;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.AlbumDetails;

import java.util.List;

public interface AlbumServiceMethods {

    List<Album> getAllAlbums();
    Album getAlbumById(Long id);
    List<Album> getAlbumsByArtist(String artist);
    List<Album> getAlbumsByYear(Integer year);
    List<Album> getAlbumsByGenre(String genre);
    Album getAlbumByName(String name);

    Album postAlbum(Album album);
    List<Album> postMultipleAlbums(List<Album> albums);

    Album updateAlbum(Album lookedUpAlbum, AlbumDetails updateDetails);

    void deleteAlbum(Long id);

}
