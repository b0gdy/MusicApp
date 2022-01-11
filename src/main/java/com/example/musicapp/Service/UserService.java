package com.example.musicapp.Service;

import com.example.musicapp.domain.User;
import com.example.musicapp.dto.UserDto;
import com.example.musicapp.dto.UserMemberDto;
import com.example.musicapp.exception.MyNoContentException;
import com.example.musicapp.mapper.UserMapper;
import com.example.musicapp.mapper.UserMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.musicapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserMemberMapper userMemberMapper;

    @Autowired
    UserRepository userRepository;

    public UserDto create(UserDto userDto) {

        User user = userMapper.mapToEntity(userDto);
        User savedUser = userRepository.save(user);

        return userMapper.mapToDto(savedUser);

    }

    public List<UserDto> getAll() {

        List<User> users = userRepository.findAll();

        return users.stream().map(u -> userMapper.mapToDto(u)).collect(Collectors.toList());

    }

    public UserDto get(String name) {

        User user = userRepository.findByName(name).orElseThrow(() -> new MyNoContentException("User not found"));
        UserDto userDto = userMapper.mapToDto(user);

        return userDto;

    }

    public UserMemberDto getMember(String name) {

        User user = userRepository.findByName(name).orElseThrow(() -> new MyNoContentException("User not found"));
        UserMemberDto userMemberDto = userMemberMapper.mapToDto(user);

        return userMemberDto;

    }

    @Transactional
    public void delete(String name) {

        userRepository.deleteByName(name);

    }

    @Transactional
    public void update(String name1, String name2) {

        userRepository.update(name1, name2);

    }

}
