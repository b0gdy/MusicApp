package com.example.musicapp.Service;

import com.example.musicapp.domain.Song;;
import com.example.musicapp.dto.SongDto;
import com.example.musicapp.dto.SongMemberDto;
import com.example.musicapp.exception.MyNoContentException;
import com.example.musicapp.mapper.SongMapper;
import com.example.musicapp.mapper.SongMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.musicapp.repository.SongRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {

    @Autowired
    SongMapper songMapper;

    @Autowired
    SongMemberMapper songMemberMapper;

    @Autowired
    SongRepository songRepository;

    public SongDto create(SongDto songDto) {

        Song song = songMapper.mapToEntity(songDto);
        Song savedSong = songRepository.save(song);

        return songMapper.mapToDto(savedSong);

    }

    public List<SongDto> getAll() {

        List<Song> songs = songRepository.findAll();

        return songs.stream().map(s -> songMapper.mapToDto(s)).collect(Collectors.toList());

    }

    public SongDto get(String name) {

        Song song = songRepository.findByName(name).orElseThrow(() -> new MyNoContentException("Song not found"));
        SongDto songDto = songMapper.mapToDto(song);

        return songDto;

    }

    public SongMemberDto getMember(String name) {

        Song song = songRepository.findByName(name).orElseThrow(() -> new MyNoContentException("Song not found"));
        SongMemberDto songMemberDto = songMemberMapper.mapToDto(song);

        return songMemberDto;

    }

    public List<SongDto> getFilteredByArtistName(String artistName) {

        List<Song> songs = songRepository.filterByArtistName(artistName).orElseThrow(() -> new MyNoContentException("No songs found"));

        return songs.stream().map(s -> songMapper.mapToDto(s)).collect(Collectors.toList());

    }

    @Transactional
    public void delete(String name) {

        songRepository.deleteByName(name);

    }

    @Transactional
    public void update(String name1, String name2) {

        songRepository.update(name1, name2);

    }
    
}
