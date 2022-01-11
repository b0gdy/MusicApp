package com.example.musicapp.Service;

import com.example.musicapp.domain.Artist;
import com.example.musicapp.dto.ArtistDto;
import com.example.musicapp.dto.ArtistMemberDto;
import com.example.musicapp.exception.MyNoContentException;
import com.example.musicapp.mapper.ArtistMapper;
import com.example.musicapp.mapper.ArtistMemberMapper;
import com.example.musicapp.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    @Autowired
    ArtistMapper artistMapper;

    @Autowired
    ArtistMemberMapper artistMemberMapper;

    @Autowired
    ArtistRepository artistRepository;

    public ArtistDto create(ArtistDto artistDto) {

        Artist artist = artistMapper.mapToEntity(artistDto);
        Artist savedArtist = artistRepository.save(artist);

        return artistMapper.mapToDto(savedArtist);

    }

    public List<ArtistDto> getAll() {

        List<Artist> artists = artistRepository.findAll();

        return artists.stream().map(s -> artistMapper.mapToDto(s)).collect(Collectors.toList());

    }

    public ArtistDto get(String name) {

        Artist artist = artistRepository.findByName(name).orElseThrow(() -> new MyNoContentException("Artist not found"));
        ArtistDto artistDto = artistMapper.mapToDto(artist);

        return artistDto;

    }

    public ArtistMemberDto getMember(String name) {

        Artist artist = artistRepository.findByName(name).orElseThrow(() -> new MyNoContentException("Artist not found"));
        ArtistMemberDto artistMemberDto = artistMemberMapper.mapToDto(artist);

        return artistMemberDto;

    }

    @Transactional
    public void delete(String name) {

        artistRepository.deleteByName(name);

    }

    @Transactional
    public void update(String name1, String name2) {

        artistRepository.update(name1, name2);

    }
    
}
