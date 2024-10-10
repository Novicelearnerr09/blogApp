package com.sayaliblog.blogappapis.controllers;

import com.sayaliblog.blogappapis.entities.User;
import com.sayaliblog.blogappapis.payloads.ApiResponse;
import com.sayaliblog.blogappapis.payloads.UserDto;
import com.sayaliblog.blogappapis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
   //post -create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {

        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }
    //PUT -update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId)
    {
        UserDto updatedUserDto = this.userService.updateUser(userDto,userId);
          return ResponseEntity.ok(updatedUserDto);
    }
    //Delete-delete user.................
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid)
    {
        this.userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);

    }
    //GET=user get

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser()
    {

        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId)
    {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
    private User dtoToUser(UserDto user1)
    {
        User user=new User();
        user.setId(user1.getId());
        user.setName(user1.getName());
        user.setEmail(user1.getEmail());
        user.setPassword(user1.getPassword());
        user.setAbout(user1.getAbout());
        return user;

    }

}
