package com.example.WebSite.service;

import com.example.WebSite.entity.User;
import com.example.WebSite.repos.UserRepository;
import com.example.WebSite.request.CreateUserRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,BCryptPasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User getUser(Long userId) {
       return userRepository.findById(userId).orElseThrow(null);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }
    public User addUser(CreateUserRequest request) {
        User newUser=User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .authorities(request.authorities())
                .accountNonLocked(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .build();
        return userRepository.save(newUser);
    }

    public User updateUser(Long userId, User newUser) {
        Optional<User> theUser=userRepository.findById(userId);
        if (theUser.isPresent()){
            User foundUser=theUser.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            foundUser.setAuthorities(newUser.getAuthorities());
            userRepository.save(foundUser);
            return foundUser;
        }else
            return null;
    }

    public void deleteUser(Long userId) {
       userRepository.deleteById(userId);
    }

}
