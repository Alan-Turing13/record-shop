package com.northcoders.record_shop.controller;

import com.northcoders.record_shop.dto.AlbumNameDTO;
import com.northcoders.record_shop.dto.ArtistNameDTO;
import com.northcoders.record_shop.exception.NotFoundException;
import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.dto.AlbumDetails;
import com.northcoders.record_shop.service.AlbumService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Locale;

@org.springframework.stereotype.Controller
@RequestMapping("api/v1/records")
public class Controller {

    @Autowired
    AlbumService albumService;

    /**********************************
     READ
     **********************************/

    @Cacheable(value = "albums")
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
    public ResponseEntity<Album> getAlbumById(@RequestParam(value = "album") @Min(1) Long id) throws NotFoundException {
        return new ResponseEntity<>(albumService.getAlbumById(id), HttpStatus.OK);
    }

    @PostMapping("/artist")
    public ResponseEntity<List<Album>> getAlbumsByArtist(
            @RequestBody @Valid ArtistNameDTO artistNameDTO
         ) throws NotFoundException {
        return new ResponseEntity<>(
                albumService.getAlbumsByArtist(artistNameDTO.artistName()), HttpStatus.OK
        );
    }

    @GetMapping("/released")
    public ResponseEntity<List<Album>> getAlbumsByYear(@RequestParam(value="year") Integer year) throws NotFoundException {
        return new ResponseEntity<>(albumService.getAlbumsByYear(year), HttpStatus.OK);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Album>> getAlbumsByGenre(
            @RequestParam(value = "gnr") @NotBlank String userInputGenre
    ) throws NotFoundException {
        return new ResponseEntity<>(
                albumService.getAlbumsByGenre(
                        userInputGenre.toUpperCase(Locale.ROOT)), HttpStatus.OK
        );
    }

    @PostMapping("/album")
    public ResponseEntity<Album> getAlbumByName(
            @RequestBody @Valid AlbumNameDTO albumNameDTO
    ) throws NotFoundException {
        System.out.println("Controller received album name " + albumNameDTO.name());
        return new ResponseEntity<>(
                albumService.getAlbumByName(albumNameDTO.name()), HttpStatus.OK
        );
    }

    /**********************************
     CREATE
     **********************************/

    @CacheEvict(value = "albums", allEntries = true)
    @PostMapping
    public ResponseEntity<Album> postAlbum(@RequestBody AlbumDetails newAlbum) {
        return new ResponseEntity<>(albumService.postAlbum(newAlbum), HttpStatus.CREATED);
    }

    /**********************************
     UPDATE
     **********************************/

    @CacheEvict(value = "albums", allEntries = true)
    @PutMapping("{id}")
    public ResponseEntity<Album> putAlbum(
            @PathVariable Long id, @RequestBody @Valid AlbumDetails albumDetails
    ) throws Exception {
        Album lookedUpAlbum = getAlbumById(id).getBody();
        if (lookedUpAlbum==null) {
            return postAlbum(albumDetails);
        } else {
            return new ResponseEntity<>(
                    albumService.updateAlbum(lookedUpAlbum, albumDetails), HttpStatus.OK
            );
        }
    }

    @CacheEvict(value = "albums", allEntries = true)
    @PatchMapping("{id}/art")
    public ResponseEntity<Album> updateAlbumArtwork(
            @PathVariable Long id, @RequestBody @Valid String imageUrl
    ) throws Exception {
        Album lookedUpAlbum = getAlbumById(id).getBody();
        if (lookedUpAlbum==null){
            return new ResponseEntity<>(new Album(), HttpStatus.NOT_FOUND);
        } else {
            lookedUpAlbum.setImageUrl(imageUrl);
            return new ResponseEntity<>(
                    albumService.updateAlbumArtwork(lookedUpAlbum), HttpStatus.OK
            );
        }
    }

    /**********************************
     DELETE
     **********************************/

    @CacheEvict(value = "albums", allEntries = true)
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long id) throws NotFoundException {
        if (!getAlbumById(id).hasBody()){
            throw new NotFoundException("No such album");
        }
        albumService.deleteAlbum(id);
        return new ResponseEntity<>("Album deleted successfully", HttpStatus.OK);
    }

}
