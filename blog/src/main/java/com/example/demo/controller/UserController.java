package com.example.demo.controller;

import com.example.demo.entity.Diary;
import com.example.demo.services.UserServicesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {
    private final UserServicesImpl services;

    public UserController(UserServicesImpl services) {
        this.services = services;
    }

    @DeleteMapping("/deleteUsers/{id}")
    public HttpStatus deleteUser(@PathVariable int id) {
        this.services.deleteUser(id);
        return HttpStatus.OK;
    }
}
