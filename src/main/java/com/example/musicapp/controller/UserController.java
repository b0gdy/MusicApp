package com.example.musicapp.controller;

import com.example.musicapp.Service.UserService;
import com.example.musicapp.dto.UserDto;
import com.example.musicapp.dto.UserMemberDto;
import com.example.musicapp.exception.MyNoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        return ResponseEntity.ok().body(userService.create(userDto));

    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {

        return ResponseEntity.ok().body(userService.getAll());

    }
    
    @GetMapping("/{name}")
    public ResponseEntity<UserDto> getUser(@PathVariable String name) {

        try {

            return ResponseEntity.ok(userService.get(name));

        } catch (MyNoContentException exception) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

    }

    @GetMapping("/member/{name}")
    public ResponseEntity<UserMemberDto> getUserMember(@PathVariable String name) {

        try {

            return ResponseEntity.ok(userService.getMember(name));

        } catch (MyNoContentException exception) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

    }

    @DeleteMapping("/{name}")
    public void deleteUser(@PathVariable String name) {

        userService.delete(name);

    }

    @PutMapping()
    public void updateUser(@RequestParam String name1,
                           @RequestParam String name2) {

        userService.update(name1, name2);

    }

}
