package com.example.musicapp.Service;

import com.example.musicapp.mapper.AddressMapper;
import com.example.musicapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    AddressRepository addressRepository;

    @Transactional
    public void delete(Integer id) {

        addressRepository.deleteById(id);

    }

    @Transactional
    public void update(Integer id, String name) {

        addressRepository.update(id, name);

    }
    
}
