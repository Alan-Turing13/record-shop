package com.northcoders.record_shop.service;

import com.northcoders.record_shop.exception.InvalidInputException;
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
            LOGGER.info("No albums found for getAllAlbums");
        }
        return albums.orElse(List.of()) ;
    }

    public Album getAlbumById(Long id) throws NotFoundException {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isEmpty()){
            String message = "No album found for getAlbumById with id " + id;
            LOGGER.info(message);
            throw new NotFoundException(message);
        }
        return album.get();
    }

    public List<Album> getAlbumsByArtist(String artist) throws  NotFoundException{
        Optional<List<Album>> albums = Optional.of(albumRepository.findAllByArtist(artist));
        if (albums.isEmpty()){
            String message = "No albums found for getAlbumsByArtist with artist " + artist;
            LOGGER.info(message);
            throw new NotFoundException(message);
        }
        return albums.get();
    }

    public List<Album> getAlbumsByYear(Integer year) throws NotFoundException{
        List<Album> albumsByYear = albumRepository.findAllByReleaseYear(year);
        if (albumsByYear.isEmpty()){
            String message = "No albums found for " + year;
            LOGGER.info(message);
            throw new NotFoundException(message);
        }
        return albumsByYear;
    }

    public List<Album> getAlbumsByGenre(String genre) throws NotFoundException{
        for (int i = 0; i < Genre.values().length; i++) {
            if (String.valueOf(Genre.values()[i]).equals(genre)) {
                return albumRepository.findAllByGenre(i);
            }
        }
        String message = "For " + genre + ", no such genre exists";
        LOGGER.info(message);
        throw new NotFoundException(message);
    }

    public Album getAlbumByName(String name) throws NotFoundException {
        Optional<Album> album = Optional.of(albumRepository.findByName(name));
        if (album.isEmpty()){
            String message = name + " not found";
            LOGGER.info(message);
            throw new NotFoundException(message);
        }
        return albumRepository.findByName(name);
    }

    /**********************************
     CREATE
     **********************************/

    public Album postAlbum(AlbumDetails album) throws Exception {
        try {
            Album existingAlbum = albumRepository.findByName(album.name());
            if (!existingAlbum.isEmpty()){
                throw new InvalidInputException("An album with that name already exists");
            }
        } catch (NullPointerException e) {}

        return albumRepository.save(
                Album.builder()
                        .name(album.name())
                        .releaseYear(album.releaseYear())
                        .genre(album.genre())
                        .artist(album.artist())
                        .imageUrl(album.imageUrl())
                        .build());
    }

    /**********************************
     UPDATE
     **********************************/

    public Album putAlbum(Long id, AlbumDetails albumDetails){
        if (albumRepository.findById(id).isEmpty()){
            return albumRepository.save(new Album(
                    id,
                    albumDetails.name(),
                    albumDetails.releaseYear(),
                    albumDetails.genre(),
                    albumDetails.artist(),
                    albumDetails.imageUrl()
            ));} else {
            Album existingAlbum = albumRepository.findById(id).get();
            existingAlbum.setName(albumDetails.name());
            existingAlbum.setReleaseYear(albumDetails.releaseYear());
            existingAlbum.setGenre(albumDetails.genre());
            existingAlbum.setArtist(albumDetails.artist());
            existingAlbum.setImageUrl(albumDetails.imageUrl());
            return albumRepository.save(existingAlbum);
        }
    }

    public Album updateAlbumArtwork(Album updatedAlbumToSave){
        LOGGER.info("Saving " + updatedAlbumToSave.getName());
        return albumRepository.save(updatedAlbumToSave);
    }

    /**********************************
     DELETE
     **********************************/

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

}
