package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.error.UserNotFoundException;

import java.util.List;

public interface UserService {
  User createUser(User user);

  List<User> getAllUsers();

  User getUserById(Long userId) throws UserNotFoundException;

  User updateUser(User user) throws UserNotFoundException;

  void deleteUser(Long userId) throws UserNotFoundException;
}
