package com.example.musicapp.Service;

import com.example.musicapp.domain.UserSong;
import com.example.musicapp.dto.UserSongDto;
import com.example.musicapp.exception.MyNoContentException;
import com.example.musicapp.mapper.UserSongMapper;
import com.example.musicapp.repository.UserSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSongService {

    @Autowired
    UserSongMapper userSongMapper;

    @Autowired
    UserSongRepository userSongRepository;

    public UserSongDto create(UserSongDto userSongDto) {

        UserSong userSong = userSongMapper.mapToEntity(userSongDto);
        userSong.setName(userSongDto.getUserId().toString() + "_" + userSongDto.getSongId().toString());
        UserSong savedUserSong = userSongRepository.save(userSong);

        return userSongMapper.mapToDto(savedUserSong);

    }

    public UserSongDto get(Integer id) {

        UserSong userSong = userSongRepository.getByIdMy(id).orElseThrow(() -> new MyNoContentException(("UserSong not found")));
//        UserSong userSong = userSongRepository.findByIdMy(id);

        return userSongMapper.mapToDto(userSong);

    }

    public List<UserSongDto> getFilteredByUserName(String userName) {

        List<UserSong> userSongs = userSongRepository.filterByUserName(userName).orElseThrow(() -> new MyNoContentException(("No UserSongs found")));

        return userSongs.stream().map(us -> userSongMapper.mapToDto(us)).collect(Collectors.toList());

    }

    public List<UserSongDto> getFilteredBySongName(String songName) {

        List<UserSong> userSongs = userSongRepository.filterBySongName(songName).orElseThrow(() -> new MyNoContentException(("No UserSongs found")));

        return userSongs.stream().map(us -> userSongMapper.mapToDto(us)).collect(Collectors.toList());

    }

    @Transactional
    public void delete(Integer id) {

        userSongRepository.deleteById(id);

    }

}
