package com.example.demo.controller;

import com.example.demo.entity.Diary;
import com.example.demo.mapper.DiaryConvertor;
import com.example.demo.services.DiaryrServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("diary/v1")//this is the base url for api.but if you don't want to use this kind of url you can avoid this line
//so this api will be accessible from http://localhost:8080/
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryrServices services;
    private final DiaryConvertor diaryConvertor;


    @Operation(summary = "получение  пользователей ")
    @GetMapping("/users")//you can give this any name you want and after adding this string to the end of base url you can use this
    public ResponseEntity<List<Diary>> getAllUsers() {
        return ResponseEntity.ok(services.getDiaryList());
    }

    @Operation(summary = "получение  пользовтеля ")
    @GetMapping("/diary/{id}")
    public ResponseEntity<Diary> getUserById(@PathVariable int id) {
        return ResponseEntity.ok().body(this.services.getDiaryById(id));
    }


    @Operation(summary = "создание  пользователя ")
    @PostMapping("/addDiary")
    public ResponseEntity<Diary> addUser(@RequestBody Diary diary) {
        return ResponseEntity.ok(this.services.createDiary(diary));
    }
    @Operation(summary = "создание  пользователей ")
    @PostMapping("/addDiarys")
    public ResponseEntity<List<Diary>> addUsers(@RequestBody List<Diary> list) {
        return ResponseEntity.ok(this.services.createDiaryList(list));
    }
    @Operation(summary = "обновление  пользовтеля ")
    @Parameters(value = {
        @Parameter(name = "setTime", description = "обновление времени", required = true),
        @Parameter(name = "setUsers", description = "обновление фио пользователя", required = true),
        @Parameter(name = "setCases", description = "обновление списка задач", required = true)})
    @PutMapping("/updateDiarys/")
    public ResponseEntity<Diary> updateUser(@RequestBody Diary diary) {
        return ResponseEntity.ok().body(this.services.updateDiaryById(diary));
    }
    @Operation(summary = "удаление  пользовтеля по id ")
    @DeleteMapping("/deleteDiarys/{id}")
    public HttpStatus deleteUser(@PathVariable int id) {
        this.services.deleteDiaryById(id);
        return HttpStatus.OK;
    }
}
