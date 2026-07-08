package com.northcoders.record_shop.config;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.repository.AlbumRepository;
import com.northcoders.record_shop.service.AlbumService;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public void run(String... args) throws Exception {
        if (albumRepository.findAll().isEmpty()){
            seedAlbums();
        }
    }

    private void seedAlbums() {
        try {
            Resource resource = new ClassPathResource("albums.txt");
            List<String> lines = Files.readAllLines(resource.getFile().toPath());

            for (int i=0; i<lines.size()-3; i+=4){
                Album album = new Album(
                        lines.get(i),
                        Integer.parseInt(lines.get(i+1)),
                        null,
                        lines.get(i+2),
                        lines.get(i+3));
                albumRepository.save(album);
                System.out.println("Added " + album.getName() + " to the database");
            }

        } catch (IOException e) {
            System.err.println("Couldn't load albums from the text file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
