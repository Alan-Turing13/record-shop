package com.northcoders.record_shop.service;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.AlbumDetails;
import com.northcoders.record_shop.model.Genre;
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
        if (albums.isEmpty()){
            System.err.println("No albums found for getAllAlbums");
        }
        return albums.orElse(List.of()) ;
    }

    public Album getAlbumById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isEmpty()){
            System.err.println("No album found for getAlbumById with id " + id);
        }
        return album.orElse(new Album());
    }

    public List<Album> getAlbumsByArtist(String artist) {
        Optional<List<Album>> albums = Optional.of(albumRepository.findAllByArtist(artist));
        if (albums.isEmpty()){
            System.err.println("No albums found for getAlbumsByArtist with artist " + artist);
        }
        return albums.orElse(List.of()) ;
    }

    public List<Album> getAlbumsByGenre(String genre) {
        return null;
    }

    public Album getAlbumByName(String name) {
        return null;
    }

    public Album postAlbum(Album album) {
        Optional<Album> savedAlbum = Optional.of(albumRepository.save(album));
        if (savedAlbum.isEmpty()){
            System.err.println("postAlbum failed for album " + album.getName());
        }
        return savedAlbum.orElse(new Album());
    }

    public List<Album> postMultipleAlbums(List<Album> albums) {
        return null;
    }

    public Album updateAlbum(Album lookedUpAlbum, AlbumDetails updateDetails) {
        System.out.println("Looked up album: " + lookedUpAlbum.getName());
        System.out.println("Details to update: " + updateDetails.artist());
        lookedUpAlbum.setName(updateDetails.name());
        lookedUpAlbum.setReleaseYear(updateDetails.releaseYear());
        lookedUpAlbum.setGenre(Genre.values()[updateDetails.genre()]);
        lookedUpAlbum.setArtist(updateDetails.artist());
        System.out.println("Updated album = " + lookedUpAlbum );
        Optional<Album> updatedAlbum = Optional.of(albumRepository.save(lookedUpAlbum));
        if (updatedAlbum.isEmpty()){
            System.err.println("updateAlbum failed for album " + lookedUpAlbum.getName());
        }
        return updatedAlbum.orElse(new Album());
    }

    public void deleteAlbum(Long id) {

    }
}
