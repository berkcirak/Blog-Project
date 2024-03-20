package com.example.WebSite.controller;

import com.example.WebSite.entity.User;
import com.example.WebSite.request.CreateUserRequest;
import com.example.WebSite.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping("/getUser/{userId}")
    public User getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }
    @GetMapping("/list")
    public List<User> listUsers(){
        return userService.listUsers();
    }
    @PostMapping("/add")
    public User addUser(@RequestBody CreateUserRequest request){
        return userService.addUser(request);
    }
    @PutMapping("/update/{userId}")
    public User updateUser(@PathVariable Long userId,@RequestBody User newUser){
        return userService.updateUser(userId,newUser);
    }
    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
