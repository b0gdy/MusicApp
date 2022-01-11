package com.example.musicapp.Service;

import com.example.musicapp.domain.Song;
import com.example.musicapp.dto.SongDto;
import com.example.musicapp.dto.SongMemberDto;
import com.example.musicapp.exception.MyNoContentException;
import com.example.musicapp.mapper.SongMapper;
import com.example.musicapp.mapper.SongMemberMapper;
import com.example.musicapp.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.musicapp.Dto.SongDtoTest.createSongDtoTest;
import static com.example.musicapp.Dto.SongMemberDtoTest.createSongMemberDtoTest;
import static com.example.musicapp.domain.SongTest.createSongTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @Mock
    private SongMapper songMapper;

    @Mock
    private SongMemberMapper songMemberMapper;

    @InjectMocks
    private SongService songService;

    @Test
    void createTest() {

        //Arrange
        SongDto songDto = createSongDtoTest("song1_test");
        Song song = createSongTest("song1_test");
        Song savedSong = createSongTest(1);

        when(songMapper.mapToEntity(songDto)).thenReturn(song);
        when(songRepository.save(song)).thenReturn(savedSong);
        when(songMapper.mapToDto(savedSong)).thenReturn(songDto);

        //Act
        SongDto result = songService.create(songDto);

        //Assert
        assertThat(result).isNotNull();
        verify(songMapper, times(1)).mapToEntity(songDto);
        verify(songMapper, times(1)).mapToDto(savedSong);
        verify(songRepository,times(1)).save(song);

        verifyNoMoreInteractions(songMapper, songRepository);

    }

    @Test
    void getAllTest() {

        //Arrange
        Song song1 = createSongTest("song1_test");
        Song song2 = createSongTest("song2_test");
        List<Song> songs = new ArrayList<>();
        songs.add(song1);
        songs.add(song2);
        SongDto songDto1 = createSongDtoTest("song1_test");
        SongDto songDto2 = createSongDtoTest("song2_test");
        List<SongDto> songDtos = new ArrayList<>();
        songDtos.add(songDto1);
        songDtos.add(songDto2);

        when(songRepository.findAll()).thenReturn(songs);
        when(songMapper.mapToDto(song1)).thenReturn(songDto1);
        when(songMapper.mapToDto(song2)).thenReturn(songDto2);

        //Act
        List<SongDto> result = songService.getAll();

        //Assert
        assertEquals(songDtos, result);

    }

    @Test
    void getTest() {

        //Arrange
        String name = "song1_test";
        SongDto songDto = createSongDtoTest(name);
        Song song = createSongTest(name);

        when(songRepository.findByName(name)).thenReturn(Optional.of(song));
        when(songMapper.mapToDto(song)).thenReturn(songDto);

        //Act
        SongDto result = songService.get(name);

        //Assert
        assertEquals(songDto, result);


    }

    @Test
    void getTest_NotFound() {

        //Arrange
        String name = "song1_test";

        when(songRepository.findByName(name)).thenReturn(Optional.empty());

        //Act
        MyNoContentException exception = assertThrows(MyNoContentException.class, () -> songService.get(name));

        //Assert
        assertEquals("Song not found", exception.getMessage());

    }

    @Test
    void getMemberTest() {

        //Arrange
        String name = "song1_test";
        SongMemberDto songMemberDto = createSongMemberDtoTest(name);
        Song song = createSongTest(name);

        when(songRepository.findByName(name)).thenReturn(Optional.of(song));
        when(songMemberMapper.mapToDto(song)).thenReturn(songMemberDto);

        //Act
        SongMemberDto result = songService.getMember(name);

        //Assert
        assertEquals(songMemberDto, result);

    }

    @Test
    void getMemberTest_NotFound() {

        //Arrange
        String name = "song1_test";

        when(songRepository.findByName(name)).thenReturn(Optional.empty());

        //Act
        MyNoContentException exception = assertThrows(MyNoContentException.class, () -> songService.getMember(name));

        //Assert
        assertEquals("Song not found", exception.getMessage());

    }

    @Test
    void getFilteredByArtistNameTest() {

        //Arrange
        String artistName = "artist1_test";
        Song song1 = createSongTest("song1_test");
        Song song2 = createSongTest("song2_test");
        List<Song> songs = new ArrayList<>();
        songs.add(song1);
        songs.add(song2);
        SongDto songDto1 = createSongDtoTest("song1_test");
        SongDto songDto2 = createSongDtoTest("song2_test");
        List<SongDto> songDtos = new ArrayList<>();
        songDtos.add(songDto1);
        songDtos.add(songDto2);

        when(songRepository.filterByArtistName(artistName)).thenReturn(Optional.of(songs));
        when(songMapper.mapToDto(song1)).thenReturn(songDto1);
        when(songMapper.mapToDto(song2)).thenReturn(songDto2);

        //Act
        List<SongDto> result = songService.getFilteredByArtistName(artistName);

        //Assert
        assertEquals(songDtos, result);

    }

    @Test
    void getFilteredByArtistNameTest_NotFound() {

        //Arrange
        String artistName = "artist1_test";

        when(songRepository.filterByArtistName(artistName)).thenReturn(Optional.empty());

        //Act
        MyNoContentException exception = assertThrows(MyNoContentException.class, () -> songService.getFilteredByArtistName(artistName));

        //Assert
        assertEquals("No songs found", exception.getMessage());

    }

    @Test
    void deleteTest() {

        String name = "song1_test";
        songRepository.deleteByName(name);

        assertThat(songRepository.count()).isEqualTo(0);
        verify(songRepository, times(1)).deleteByName(name);

    }

    @Test
    void updateTest() {

        String name1 = "song1_test";
        String name2 = "song2_test";
        songRepository.update(name1, name2);

        assertThat(songRepository.count()).isEqualTo(0);
        verify(songRepository, times(1)).update(name1, name2);

    }

}
