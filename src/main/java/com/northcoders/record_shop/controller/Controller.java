package com.northcoders.record_shop.controller;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/records")
public class Controller {

    @Autowired
    AlbumService albumService;

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAllAlbums(){
        return new ResponseEntity<>(albumService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Album> getAlbumById(@RequestParam(value = "album") Long id){
        return new ResponseEntity<>(albumService.getAlbumById(id), HttpStatus.OK);
    }

    @GetMapping("/artist")
    public ResponseEntity<List<Album>> getAlbumsByArtist(@RequestParam(value="name") String artistName){
        return new ResponseEntity<>(albumService.getAlbumsByArtist(artistName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Album> postAlbum(@RequestBody Album newAlbum){
        return new ResponseEntity<>(albumService.postAlbum(newAlbum), HttpStatus.CREATED);
    }

}
