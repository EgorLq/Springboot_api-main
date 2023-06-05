package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.maper.UserMapper;
import com.example.demo.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Api(tags = "Управление пользователями")
public class UserController {
  private final UserService userService;
  private final UserMapper userMapper;


  @PostMapping
  @ApiOperation(value = "Создать пользователя")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Пользователь успешно создан"),
        @ApiResponse(code = 400, message = "Некорректный запрос"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
      })
  public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    User user = userMapper.userDTOToUser(userDTO);
    User createdUser = userService.createUser(user);
    UserDTO createdUserDTO = userMapper.userToUserDTO(createdUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
  }
  @GetMapping
  @ApiOperation(value = "Получить список всех пользователей")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Пользователи успешно получены"),
        @ApiResponse(code = 404, message = "Пользователи не найдены"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
      })
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    List<UserDTO> userDTOs = users.stream()
            .map(userMapper::userToUserDTO)
            .collect(Collectors.toList());
    return ResponseEntity.ok(userDTOs);
  }

  @GetMapping("/{userId}")
  @ApiOperation(value = "Получить пользователя по ID")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Пользователь успешно получен"),
        @ApiResponse(code = 404, message = "Пользователь не найден"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
      })
  public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userId) {
    User user = userService.getUserById(userId);
    UserDTO userDTO = userMapper.userToUserDTO(user);
    return ResponseEntity.ok(userDTO);
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
  public ResponseEntity<UserDTO> updateUser(
          @PathVariable("userId") Long userId, @RequestBody UserDTO userDTO) {
    User user = userMapper.userDTOToUser(userDTO);
    user.setId(userId);
    User updatedUser = userService.updateUser(user);
    UserDTO updatedUserDTO = userMapper.userToUserDTO(updatedUser);
    return ResponseEntity.ok(updatedUserDTO);
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
    userService.deleteUser(userId);
    return ResponseEntity.noContent().build();
  }
}
