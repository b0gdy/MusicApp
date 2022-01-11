package com.example.musicapp.controller;

import com.example.musicapp.Service.UserSongService;
import com.example.musicapp.dto.UserSongDto;
import com.example.musicapp.exception.MyNoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users_songs")
public class UserSongController {

    @Autowired
    private UserSongService userSongService;

    @PostMapping()
    public ResponseEntity<UserSongDto> createUserSong(@RequestBody UserSongDto userSongDto) {

        return ResponseEntity.ok().body(userSongService.create(userSongDto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSongDto> getUserSong(@PathVariable Integer id) {

        try {

            return ResponseEntity.ok(userSongService.get(id));

        } catch (MyNoContentException exception) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
//        return ResponseEntity.ok(userSongService.get(id));

    }

    @GetMapping("/filterByUserName")
    public ResponseEntity<List<UserSongDto>> getUserSongByUserName(@RequestParam String userName) {

        try {

            return ResponseEntity.ok().body(userSongService.getFilteredByUserName(userName));

        } catch (MyNoContentException exception) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

    }

    @GetMapping("/filterBySongName")
    public ResponseEntity<List<UserSongDto>> getUserSongBySongName(@RequestParam String songName) {

        try {

            return ResponseEntity.ok().body(userSongService.getFilteredBySongName(songName));

        } catch (MyNoContentException exception) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

    }

    @DeleteMapping("/{id}")
    public void deleteUserSong(@PathVariable Integer id) {

        userSongService.delete(id);

    }

}
