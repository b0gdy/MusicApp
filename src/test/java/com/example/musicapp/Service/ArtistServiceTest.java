package com.example.musicapp.Service;

import com.example.musicapp.domain.Artist;
import com.example.musicapp.dto.ArtistDto;
import com.example.musicapp.dto.ArtistMemberDto;
import com.example.musicapp.exception.MyNoContentException;
import com.example.musicapp.mapper.ArtistMapper;
import com.example.musicapp.mapper.ArtistMemberMapper;
import com.example.musicapp.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.musicapp.Dto.ArtistDtoTest.createArtistDtoTest;
import static com.example.musicapp.Dto.ArtistMemberDtoTest.createArtistMemberDtoTest;
import static com.example.musicapp.domain.ArtistTest.createArtistTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private ArtistMapper artistMapper;

    @Mock
    private ArtistMemberMapper artistMemberMapper;

    @InjectMocks
    private ArtistService artistService;

    @Test
    void createTest() {

        //Arrange
        ArtistDto artistDto = createArtistDtoTest("artist1_test");
        Artist artist = createArtistTest("artist1_test");
        Artist savedArtist = createArtistTest(1);

        when(artistMapper.mapToEntity(artistDto)).thenReturn(artist);
        when(artistRepository.save(artist)).thenReturn(savedArtist);
        when(artistMapper.mapToDto(savedArtist)).thenReturn(artistDto);

        //Act
        ArtistDto result = artistService.create(artistDto);

        //Assert
        assertThat(result).isNotNull();
        verify(artistMapper, times(1)).mapToEntity(artistDto);
        verify(artistMapper, times(1)).mapToDto(savedArtist);
        verify(artistRepository,times(1)).save(artist);

        verifyNoMoreInteractions(artistMapper, artistRepository);

    }

    @Test
    void getAllTest() {

        //Arrange
        Artist artist1 = createArtistTest("artist1_test");
        Artist artist2 = createArtistTest("artist2_test");
        List<Artist> artists = new ArrayList<>();
        artists.add(artist1);
        artists.add(artist2);
        ArtistDto artistDto1 = createArtistDtoTest("artist1_test");
        ArtistDto artistDto2 = createArtistDtoTest("artist2_test");
        List<ArtistDto> artistDtos = new ArrayList<>();
        artistDtos.add(artistDto1);
        artistDtos.add(artistDto2);

        when(artistRepository.findAll()).thenReturn(artists);
        when(artistMapper.mapToDto(artist1)).thenReturn(artistDto1);
        when(artistMapper.mapToDto(artist2)).thenReturn(artistDto2);

        //Act
        List<ArtistDto> result = artistService.getAll();

        //Assert
        assertEquals(artistDtos, result);

    }

    @Test
    void getTest() {

        //Arrange
        String name = "artist1_test";
        ArtistDto artistDto = createArtistDtoTest(name);
        Artist artist = createArtistTest(name);

        when(artistRepository.findByName(name)).thenReturn(Optional.of(artist));
        when(artistMapper.mapToDto(artist)).thenReturn(artistDto);

        //Act
        ArtistDto result = artistService.get(name);

        //Assert
        assertEquals(artistDto, result);


    }

    @Test
    void getTest_NotFound() {

        //Arrange
        String name = "artist1_test";

        when(artistRepository.findByName(name)).thenReturn(Optional.empty());

        //Act
        MyNoContentException exception = assertThrows(MyNoContentException.class, () -> artistService.get(name));

        //Assert
        assertEquals("Artist not found", exception.getMessage());

    }

    @Test
    void getMemberTest() {

        //Arrange
        String name = "artist1_test";
        ArtistMemberDto artistMemberDto = createArtistMemberDtoTest(name);
        Artist artist = createArtistTest(name);

        when(artistRepository.findByName(name)).thenReturn(Optional.of(artist));
        when(artistMemberMapper.mapToDto(artist)).thenReturn(artistMemberDto);

        //Act
        ArtistMemberDto result = artistService.getMember(name);

        //Assert
        assertEquals(artistMemberDto, result);

    }

    @Test
    void getMemberTest_NotFound() {

        //Arrange
        String name = "artist1_test";

        when(artistRepository.findByName(name)).thenReturn(Optional.empty());

        //Act
        MyNoContentException exception = assertThrows(MyNoContentException.class, () -> artistService.getMember(name));

        //Assert
        assertEquals("Artist not found", exception.getMessage());

    }

    @Test
    void deleteTest() {

        String name = "artist1_test";
        artistRepository.deleteByName(name);

        assertThat(artistRepository.count()).isEqualTo(0);
        verify(artistRepository, times(1)).deleteByName(name);

    }

    @Test
    void updateTest() {

        String name1 = "artist1_test";
        String name2 = "artist2_test";
        artistRepository.update(name1, name2);

        assertThat(artistRepository.count()).isEqualTo(0);
        verify(artistRepository, times(1)).update(name1, name2);

    }

}
