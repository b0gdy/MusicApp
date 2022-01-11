package com.example.musicapp.controller;

import com.example.musicapp.Service.SongService;
import com.example.musicapp.dto.SongDto;
import com.example.musicapp.dto.SongMemberDto;
import com.example.musicapp.exception.MyNoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping()
    public ResponseEntity<SongDto> createSong(@RequestBody SongDto songDto) {

        return ResponseEntity.ok().body(songService.create(songDto));

    }

    @GetMapping()
    public ResponseEntity<List<SongDto>> getAllSongs() {

        return ResponseEntity.ok().body(songService.getAll());

    }

    @GetMapping("/{name}")
    public ResponseEntity<SongDto> getSong(@PathVariable String name) {

        try {

            return ResponseEntity.ok(songService.get(name));

        } catch (MyNoContentException exception) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

    }

    @GetMapping("/member/{name}")
    public ResponseEntity<SongMemberDto> getSongMember(@PathVariable String name) {

        try {

            return ResponseEntity.ok(songService.getMember(name));

        } catch (MyNoContentException exception) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

    }

    @GetMapping("/filterByArtistName")
    public ResponseEntity<List<SongDto>> getUserSongByArtistName(@RequestParam String artistName) {

        try {

            return ResponseEntity.ok().body(songService.getFilteredByArtistName(artistName));

        } catch (MyNoContentException exception) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

    }

    @DeleteMapping("/{name}")
    public void deleteSong(@PathVariable String name) {

        songService.delete(name);

    }

    @PutMapping()
    public void updateSong(@RequestParam String name1,
                           @RequestParam String name2) {

        songService.update(name1, name2);

    }
    
}
