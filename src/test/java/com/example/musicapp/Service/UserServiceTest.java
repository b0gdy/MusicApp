package com.example.musicapp.Service;

import com.example.musicapp.domain.User;
import com.example.musicapp.dto.UserDto;
import com.example.musicapp.dto.UserMemberDto;
import com.example.musicapp.exception.MyNoContentException;
import com.example.musicapp.mapper.UserMapper;
import com.example.musicapp.mapper.UserMemberMapper;
import com.example.musicapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.musicapp.Dto.UserDtoTest.createUserDtoTest;
import static com.example.musicapp.Dto.UserMemberDtoTest.createUserMemberDtoTest;
import static com.example.musicapp.domain.UserTest.createUserTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserMemberMapper userMemberMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void createTest() {

        //Arrange
        UserDto userDto = createUserDtoTest("user1_test");
        User user = createUserTest("user1_test");
        User savedUser = createUserTest(1);

        when(userMapper.mapToEntity(userDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.mapToDto(savedUser)).thenReturn(userDto);

        //Act
        UserDto result = userService.create(userDto);

        //Assert
        assertThat(result).isNotNull();
        verify(userMapper, times(1)).mapToEntity(userDto);
        verify(userMapper, times(1)).mapToDto(savedUser);
        verify(userRepository,times(1)).save(user);

        verifyNoMoreInteractions(userMapper, userRepository);

    }

    @Test
    void getAllTest() {

        //Arrange
        User user1 = createUserTest("user1_test");
        User user2 = createUserTest("user2_test");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        UserDto userDto1 = createUserDtoTest("user1_test");
        UserDto userDto2 = createUserDtoTest("user2_test");
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto1);
        userDtos.add(userDto2);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.mapToDto(user1)).thenReturn(userDto1);
        when(userMapper.mapToDto(user2)).thenReturn(userDto2);

        //Act
        List<UserDto> result = userService.getAll();

        //Assert
        assertEquals(userDtos, result);

    }

    @Test
    void getTest() {

        //Arrange
        String name = "user1_test";
        UserDto userDto = createUserDtoTest(name);
        User user = createUserTest(name);

        when(userRepository.findByName(name)).thenReturn(Optional.of(user));
        when(userMapper.mapToDto(user)).thenReturn(userDto);

        //Act
        UserDto result = userService.get(name);

        //Assert
        assertEquals(userDto, result);


    }

    @Test
    void getTest_NotFound() {

        //Arrange
        String name = "user1_test";

        when(userRepository.findByName(name)).thenReturn(Optional.empty());

        //Act
        MyNoContentException exception = assertThrows(MyNoContentException.class, () -> userService.get(name));

        //Assert
        assertEquals("User not found", exception.getMessage());

    }

    @Test
    void getMemberTest() {

        //Arrange
        String name = "user1_test";
        UserMemberDto userMemberDto = createUserMemberDtoTest(name);
        User user = createUserTest(name);

        when(userRepository.findByName(name)).thenReturn(Optional.of(user));
        when(userMemberMapper.mapToDto(user)).thenReturn(userMemberDto);

        //Act
        UserMemberDto result = userService.getMember(name);

        //Assert
        assertEquals(userMemberDto, result);

    }

    @Test
    void getMemberTest_NotFound() {

        //Arrange
        String name = "user1_test";

        when(userRepository.findByName(name)).thenReturn(Optional.empty());

        //Act
        MyNoContentException exception = assertThrows(MyNoContentException.class, () -> userService.getMember(name));

        //Assert
        assertEquals("User not found", exception.getMessage());

    }

    @Test
    void deleteTest() {

        String name = "user1_test";
        userRepository.deleteByName(name);

        assertThat(userRepository.count()).isEqualTo(0);
        verify(userRepository, times(1)).deleteByName(name);

    }

    @Test
    void updateTest() {

        String name1 = "user1_test";
        String name2 = "user2_test";
        userRepository.update(name1, name2);

        assertThat(userRepository.count()).isEqualTo(0);
        verify(userRepository, times(1)).update(name1, name2);

    }

}
