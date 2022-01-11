package com.example.musicapp.controller;

import com.example.musicapp.Service.ArtistService;
import com.example.musicapp.dto.ArtistDto;
import com.example.musicapp.dto.ArtistMemberDto;
import com.example.musicapp.exception.MyNoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @PostMapping()
    public ResponseEntity<ArtistDto> createArtist(@RequestBody ArtistDto artistDto) {

        return ResponseEntity.ok().body(artistService.create(artistDto));

    }

    @GetMapping()
    public ResponseEntity<List<ArtistDto>> getAllArtists() {

        return ResponseEntity.ok().body(artistService.getAll());

    }

    @GetMapping("/{name}")
    public ResponseEntity<ArtistDto> getArtist(@PathVariable String name) {

        try {

            return ResponseEntity.ok(artistService.get(name));

        } catch (MyNoContentException exception) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

    }

    @GetMapping("/member/{name}")
    public ResponseEntity<ArtistMemberDto> getArtistMember(@PathVariable String name) {

        try {

            return ResponseEntity.ok(artistService.getMember(name));

        } catch (MyNoContentException exception) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

    }

    @DeleteMapping("/{name}")
    public void deleteArtist(@PathVariable String name) {

        artistService.delete(name);

    }

    @PutMapping()
    public void updateArtist(@RequestParam String name1,
                             @RequestParam String name2) {

        artistService.update(name1, name2);

    }
    
}
