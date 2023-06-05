package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.error.UserNotFoundException;

import java.util.List;

public interface UserService {

  /**
   * Создает нового пользователя.
   *
   * @param user пользователь для создания
   * @return созданный пользователь
   */
  User createUser(User user);
  /**
   * Возвращает список всех пользователей.
   *
   * @return список всех пользователей
   */
  List<User> getAllUsers();
  /**
   * Возвращает пользователя по указанному идентификатору.
   *
   * @param userId идентификатор пользователя
   * @return найденный пользователь
   * @throws UserNotFoundException если пользователь с указанным идентификатором не найден
   */
  User getUserById(Long userId) throws UserNotFoundException;

  /**
   * Обновляет информацию о пользователе.
   *
   * @param user пользователь с обновленными данными
   * @return обновленный пользователь
   * @throws UserNotFoundException если пользователь для обновления не найден
   */
  User updateUser(User user) throws UserNotFoundException;
  /**
   * Удаляет пользователя по указанному идентификатору.
   *
   * @param userId идентификатор пользователя для удаления
   * @throws UserNotFoundException если пользователь с указанным идентификатором не найден
   */
  void deleteUser(Long userId) throws UserNotFoundException;
}
