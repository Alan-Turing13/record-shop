package com.northcoders.record_shop.service;

import com.northcoders.record_shop.exception.NotFoundException;
import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.dto.AlbumDetails;
import com.northcoders.record_shop.model.Genre;
import com.northcoders.record_shop.repository.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumService{

    private final Logger LOGGER = LoggerFactory.getLogger(AlbumService.class);

    @Autowired
    AlbumRepository albumRepository;

    /**********************************
    READ
     **********************************/

    public List<Album> getAllAlbums() {
        LOGGER.info("get all albums call received");
        Optional<List<Album>> albums = Optional.of(albumRepository.findAll());
        if (albums.isEmpty()){
            System.err.println("No albums found for getAllAlbums");
        }
        return albums.orElse(List.of()) ;
    }

    public Album getAlbumById(Long id) throws NotFoundException {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isEmpty()){
            String message = "No album found for getAlbumById with id " + id;
            System.err.println(message);
            throw new NotFoundException(message);
        }
        return album.get();
    }

    public List<Album> getAlbumsByArtist(String artist) throws  NotFoundException{
        Optional<List<Album>> albums = Optional.of(albumRepository.findAllByArtist(artist));
        if (albums.isEmpty()){
            String message = "No albums found for getAlbumsByArtist with artist " + artist;
            System.err.println(message);
            throw new NotFoundException(message);
        }
        return albums.get();
    }

    public List<Album> getAlbumsByYear(Integer year) throws NotFoundException{
        Optional<List<Album>> albums = Optional.of(albumRepository.findAllByReleaseYear(year));
        if (albums.isEmpty()){
            String message = "No albums found for " + year;
            System.err.println(message);
            throw new NotFoundException(message);
        }
        return albums.get();
    }

    public List<Album> getAlbumsByGenre(String genre) throws NotFoundException{
        List<Album> returnList = new ArrayList<Album>();
        for (int i = 0; i < Genre.values().length; i++) {
            if (String.valueOf(Genre.values()[i]).equals(genre)) {
                System.out.println("Returning all albums of genre " + i + " from the db");
                returnList = albumRepository.findAllByGenre(i);
            } else {
                String message = "No such genre exists";
                System.err.println(message);
                throw new NotFoundException(message);
            }
        }
        return returnList;
    }

    public Album getAlbumByName(String name) throws NotFoundException {
        Optional<Album> album = Optional.of(albumRepository.findByName(name));
        if (album.isEmpty()){
            String message = name + " not found";
            System.err.println(message);
            throw new NotFoundException(message);
        }
        return albumRepository.findByName(name);
    }

    /**********************************
     CREATE
     **********************************/

    public Album postAlbum(AlbumDetails album) {
        Optional<Album> savedAlbum = Optional.of(albumRepository.save(
                Album.builder()
                    .name(album.name())
                    .releaseYear(album.releaseYear())
                    .genre(album.genre())
                    .artist(album.artist())
                    .imageUrl(album.imageUrl())
                    .build()));
        return savedAlbum.orElse(new Album());
    }

    /**********************************
     UPDATE
     **********************************/

    public Album updateAlbum(Album lookedUpAlbum, AlbumDetails updateDetails) {

        lookedUpAlbum.setName(updateDetails.name());
        lookedUpAlbum.setReleaseYear(updateDetails.releaseYear());
        lookedUpAlbum.setGenre(updateDetails.genre());
        lookedUpAlbum.setArtist(updateDetails.artist());
        lookedUpAlbum.setImageUrl(updateDetails.imageUrl());

        return albumRepository.save(lookedUpAlbum);
    }

    public Album updateAlbumArtwork(Album updatedAlbumToSave){
        return albumRepository.save(updatedAlbumToSave);
    }

    /**********************************
     DELETE
     **********************************/

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

}
