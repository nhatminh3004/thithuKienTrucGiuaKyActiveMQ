package com.example.demo.service;


import com.example.demo.authen.UserPrincipal;
import com.example.demo.entity.User;

public interface UserService {
    User createUser(User user);
    UserPrincipal findByUsername(String username);
    User saveUser(User user);
    User getUserByUsername(String username);
}
