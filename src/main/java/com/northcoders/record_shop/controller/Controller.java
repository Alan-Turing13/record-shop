package com.northcoders.record_shop.controller;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.AlbumDetails;
import com.northcoders.record_shop.model.Genre;
import com.northcoders.record_shop.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("api/v1/records")
public class Controller {

    @Autowired
    AlbumService albumService;

    /**********************************
     READ
     **********************************/

    @GetMapping("/albums/response")
    public ResponseEntity<List<Album>> getAllAlbums(){
        return new ResponseEntity<>(albumService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping("/albums")
    public String getAlbums(Model model){
        List<Album> albums = albumService.getAllAlbums();
        model.addAttribute("albums", albums);
        return "/home.html";
    }

    @GetMapping()
    public ResponseEntity<Album> getAlbumById(@RequestParam(value = "album") Long id){
        return new ResponseEntity<>(albumService.getAlbumById(id), HttpStatus.OK);
    }

    @GetMapping("/artist")
    public ResponseEntity<List<Album>> getAlbumsByArtist(@RequestParam(value="name") String artistName){
        return new ResponseEntity<>(albumService.getAlbumsByArtist(artistName), HttpStatus.OK);
    }

    @GetMapping("/released")
    public ResponseEntity<List<Album>> getAlbumsByYear(@RequestParam(value="year") Integer year){
        return new ResponseEntity<>(albumService.getAlbumsByYear(year), HttpStatus.OK);
    }

    /**********************************
     CREATE
     **********************************/

    @PostMapping
    public ResponseEntity<Album> postAlbum(@RequestBody Album newAlbum){
        return new ResponseEntity<>(albumService.postAlbum(newAlbum), HttpStatus.CREATED);
    }

    /**********************************
     UPDATE
     **********************************/

    @PutMapping("{id}")
    public ResponseEntity<Album> putAlbum(@PathVariable Long id, @RequestBody AlbumDetails albumDetails){
        System.out.println("received put request");
        Album lookedUpAlbum = getAlbumById(id).getBody();
        if (lookedUpAlbum==null) {
            return postAlbum(new Album(
                    id,
                    albumDetails.name(),
                    albumDetails.releaseYear(),
                    Genre.values()[albumDetails.genre()],
                    albumDetails.artist())
            );
        } else {
            return new ResponseEntity<>(albumService.updateAlbum(lookedUpAlbum, albumDetails), HttpStatus.OK);
        }
    }

    @PatchMapping("{id}/art")
    public ResponseEntity<Album> updateAlbumArtwork(@PathVariable Long id, @RequestBody String imageUrl){
        Album lookedUpAlbum = getAlbumById(id).getBody();
        if (lookedUpAlbum==null){
            return new ResponseEntity<>(new Album(), HttpStatus.NOT_FOUND);
        } else {
            lookedUpAlbum.setImageUrl(imageUrl);
            return new ResponseEntity<>(albumService.updateAlbumArtwork(lookedUpAlbum), HttpStatus.OK);
        }
    }

    /**********************************
     DELETE
     **********************************/

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long id){
        albumService.deleteAlbum(id);
        return new ResponseEntity<>("Album deleted successfully", HttpStatus.OK);
    }

}
