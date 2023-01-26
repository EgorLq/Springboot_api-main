package com.example.demo.services;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;

import java.util.Optional;

public interface UserServices {
    UserDTO saveUser (UserDTO request);
    UserDTO updateUser (User request);
    Optional<User> getUserById (Integer id) throws Throwable;
    void  deleteUser (Integer id) ;
}
