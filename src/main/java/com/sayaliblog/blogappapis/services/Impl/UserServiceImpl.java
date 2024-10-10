package com.sayaliblog.blogappapis.services.Impl;

import com.sayaliblog.blogappapis.entities.User;
import com.sayaliblog.blogappapis.exceptions.ResourceNotFoundException;
import com.sayaliblog.blogappapis.payloads.UserDto;
import com.sayaliblog.blogappapis.repositories.UserRepo;
import com.sayaliblog.blogappapis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.sayaliblog.blogappapis.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
       User savedUser=this.userRepo.save(user);
        return this.userTodto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userid) {
         User user1 = this.userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User","id",userid));

       user1.setName(user.getName());
       user1.setEmail(user.getEmail());
       user1.setPassword(user.getPassword());
       user1.setAbout(user.getAbout());

            User saveduser =this.userRepo.save(user1);
            return this.userTodto(saveduser);
    }

    @Override
    public UserDto getUserById(Integer userid) {

        User user1 = this.userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User","id",userid));
        return this.userTodto(user1);
    }


    @Override
    public List<UserDto> getAllUsers() {
//        List<User> users = this.userRepo.findAll();
//        List<UserDto> userDtos = new ArrayList<>();
//        for (User user : users) {
//            userDtos.add(this.userTodto(user));
//        }
//        Long userId=users.get(0).getId();
//
//       return userDtos;
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(user->this.userTodto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userid) {
        User user1 = this.userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User","id",userid));
        this.userRepo.delete(user1);
    }
    private User dtoToUser(UserDto user1)
    {
        User user=this.modelMapper.map(user1,User.class);
//        user.setId(user1.getId());
//        user.setName(user1.getName());
//        user.setEmail(user1.getEmail());
//        user.setPassword(user1.getPassword());
//        user.setAbout(user1.getAbout());
        return user;

    }
    private UserDto userTodto(User user)
    {
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;

    }
}
