package com.example.musicapp.Service;

import com.example.musicapp.domain.UserSong;
import com.example.musicapp.dto.UserSongDto;
import com.example.musicapp.exception.MyNoContentException;
import com.example.musicapp.mapper.UserSongMapper;
import com.example.musicapp.repository.UserSongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.musicapp.Dto.UserSongDtoTest.createUserSongDtoTest;
import static com.example.musicapp.domain.UserSongTest.createUserSongTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class UserSongServiceTest {

    @Mock
    private UserSongRepository userSongRepository;

    @Mock
    private UserSongMapper userSongMapper;

    @InjectMocks
    private UserSongService userSongService;

    @Test
    void createTest() {

        //Arrange
        UserSongDto userSongDto = createUserSongDtoTest(1, 1);
        UserSong userSong = createUserSongTest(1, 1);
        UserSong savedUserSong = createUserSongTest(1);

        when(userSongMapper.mapToEntity(userSongDto)).thenReturn(userSong);
        when(userSongRepository.save(userSong)).thenReturn(savedUserSong);
        when(userSongMapper.mapToDto(savedUserSong)).thenReturn(userSongDto);

        //Act
        UserSongDto result = userSongService.create(userSongDto);

        //Assert
        assertThat(result).isNotNull();
        verify(userSongMapper, times(1)).mapToEntity(userSongDto);
        verify(userSongMapper, times(1)).mapToDto(savedUserSong);
        verify(userSongRepository,times(1)).save(userSong);

        verifyNoMoreInteractions(userSongMapper, userSongRepository);

    }

    @Test
    void getTest() {

        //Arrange
        Integer id = 1;
        UserSongDto userSongDto = createUserSongDtoTest(1, 1);
        UserSong userSong = createUserSongTest(1, 1);

        when(userSongRepository.getByIdMy(id)).thenReturn(Optional.of(userSong));
        when(userSongMapper.mapToDto(userSong)).thenReturn(userSongDto);

        //Act
        UserSongDto result = userSongService.get(id);

        //Assert
        assertEquals(userSongDto, result);


    }

    @Test
    void getTest_NotFound() {

        //Arrange
        Integer id = 1;
        when(userSongRepository.getByIdMy(id)).thenReturn(Optional.empty());

        //Act
        MyNoContentException exception = assertThrows(MyNoContentException.class, () -> userSongService.get(id));

        //Assert
        assertEquals("UserSong not found", exception.getMessage());

    }

    @Test
    void getFilteredByUserNameTest() {

        //Arrange
        String userName = "user1_test";
        UserSong userSong1 = createUserSongTest(1, 1);
        UserSong userSong2 = createUserSongTest(1, 2);
        List<UserSong> userSongs = new ArrayList<>();
        userSongs.add(userSong1);
        userSongs.add(userSong2);
        UserSongDto userSongDto1 = createUserSongDtoTest(1, 1);
        UserSongDto userSongDto2 = createUserSongDtoTest(1, 2);
        List<UserSongDto> userSongDtos = new ArrayList<>();
        userSongDtos.add(userSongDto1);
        userSongDtos.add(userSongDto2);

        when(userSongRepository.filterByUserName(userName)).thenReturn(Optional.of(userSongs));
        when(userSongMapper.mapToDto(userSong1)).thenReturn(userSongDto1);
        when(userSongMapper.mapToDto(userSong2)).thenReturn(userSongDto2);

        //Act
        List<UserSongDto> result = userSongService.getFilteredByUserName(userName);

        //Assert
        assertEquals(userSongDtos, result);

    }

    @Test
    void getFilteredByUserNameTest_NotFound() {

        //Arrange
        String userName = "user1_test";

        when(userSongRepository.filterByUserName(userName)).thenReturn(Optional.empty());

        //Act
        MyNoContentException exception = assertThrows(MyNoContentException.class, () -> userSongService.getFilteredByUserName(userName));

        //Assert
        assertEquals("No UserSongs found", exception.getMessage());

    }

    @Test
    void getFilteredBySongNameTest() {

        //Arrange
        String songName = "song1_test";
        UserSong userSong1 = createUserSongTest(1, 1);
        UserSong userSong2 = createUserSongTest(2, 1);
        List<UserSong> userSongs = new ArrayList<>();
        userSongs.add(userSong1);
        userSongs.add(userSong2);
        UserSongDto userSongDto1 = createUserSongDtoTest(1, 1);
        UserSongDto userSongDto2 = createUserSongDtoTest(2, 1);
        List<UserSongDto> userSongDtos = new ArrayList<>();
        userSongDtos.add(userSongDto1);
        userSongDtos.add(userSongDto2);

        when(userSongRepository.filterBySongName(songName)).thenReturn(Optional.of(userSongs));
        when(userSongMapper.mapToDto(userSong1)).thenReturn(userSongDto1);
        when(userSongMapper.mapToDto(userSong2)).thenReturn(userSongDto2);

        //Act
        List<UserSongDto> result = userSongService.getFilteredBySongName(songName);

        //Assert
        assertEquals(userSongDtos, result);

    }

    @Test
    void getFilteredBySongNameTest_NotFound() {

        //Arrange
        String songName = "song1_test";

        when(userSongRepository.filterBySongName(songName)).thenReturn(Optional.empty());

        //Act
        MyNoContentException exception = assertThrows(MyNoContentException.class, () -> userSongService.getFilteredBySongName(songName));

        //Assert
        assertEquals("No UserSongs found", exception.getMessage());

    }

    @Test
    void deleteTest() {

        Integer id = 1;
        userSongRepository.deleteById(id);

        assertThat(userSongRepository.count()).isEqualTo(0);
        verify(userSongRepository, times(1)).deleteById(id);

    }

}