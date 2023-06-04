package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.services.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Api(tags = "Управление пользователями")
public class UserController {

  private final UserServiceImpl userServiceImpl;

  @PostMapping
  @ApiOperation(value = "Создать пользователя")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Пользователь успешно создан"),
        @ApiResponse(code = 400, message = "Некорректный запрос"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
      })
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User createdUser = userServiceImpl.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

  @GetMapping
  @ApiOperation(value = "Получить список всех пользователей")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Пользователи успешно получены"),
        @ApiResponse(code = 404, message = "Пользователи не найдены"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
      })
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userServiceImpl.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{userId}")
  @ApiOperation(value = "Получить пользователя по ID")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Пользователь успешно получен"),
        @ApiResponse(code = 404, message = "Пользователь не найден"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
      })
  public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
    User user = userServiceImpl.getUserById(userId);
    return ResponseEntity.ok(user);
  }

  @PutMapping("/{userId}")
  @ApiOperation(value = "Обновить пользователя")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Пользователь успешно обновлен"),
        @ApiResponse(code = 400, message = "Некорректный запрос"),
        @ApiResponse(code = 404, message = "Пользователь не найден"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
      })
  public ResponseEntity<User> updateUser(
      @PathVariable("userId") Long userId, @RequestBody User user) {
    user.setId(userId);
    User updatedUser = userServiceImpl.updateUser(user);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/{userId}")
  @ApiOperation(value = "Удалить пользователя")
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "Пользователь успешно удален"),
        @ApiResponse(code = 404, message = "Пользователь не найден"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
      })
  public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
    userServiceImpl.deleteUser(userId);
    return ResponseEntity.noContent().build();
  }
}
