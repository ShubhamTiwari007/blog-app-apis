package com.blog.app.services.impl;

import com.blog.app.entities.User;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.UserDto;
import com.blog.app.repositries.UserRepo;
import com.blog.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    private User dtoToUser(UserDto userDto) {
        return this.modelMapper.map(userDto,User.class);
    }

    private UserDto userToDto(User user) {
        return this.modelMapper.map(user,UserDto.class);
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUser = this.userRepo.save(user);

        UserDto userDto1 = userToDto(updateUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));

        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();

        return users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        this.userRepo.delete(user);
    }
}
