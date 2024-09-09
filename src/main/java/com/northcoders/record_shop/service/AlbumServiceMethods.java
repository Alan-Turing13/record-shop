package com.northcoders.record_shop.service;

import com.northcoders.record_shop.exception.NotFoundException;
import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.dto.AlbumDetails;

import java.util.List;

public interface AlbumServiceMethods {

    List<Album> getAllAlbums();
    Album getAlbumById(Long id) throws NotFoundException;
    List<Album> getAlbumsByArtist(String artist) throws NotFoundException;
    List<Album> getAlbumsByYear(Integer year) throws NotFoundException;
    List<Album> getAlbumsByGenre(String genre) throws NotFoundException;
    Album getAlbumByName(String name) throws NotFoundException;

    Album postAlbum(Album album);
    List<Album> postMultipleAlbums(List<Album> albums);

    Album updateAlbum(Album lookedUpAlbum, AlbumDetails updateDetails);

    void deleteAlbum(Long id);

}
