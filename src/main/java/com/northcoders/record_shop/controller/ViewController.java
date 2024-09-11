package com.northcoders.record_shop.controller;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/v1/records")
public class ViewController {

    @Autowired
    AlbumService albumService;

    @GetMapping("/albums")
    public String getAlbums(Model model){
        List<Album> albums = albumService.getAllAlbums();
        model.addAttribute("albums", albums);
        return "/home.html";
    }
}
