package com.example.musicapp.controller;

import com.example.musicapp.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Integer id) {

        addressService.delete(id);

    }

    @PutMapping()
    public void updateAddress(@RequestParam Integer id,
                              @RequestParam String name) {

        addressService.update(id, name);

    }

}
