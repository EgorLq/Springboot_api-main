package com.example.demo.services.impl;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User createUser(User user) {
        log.info("Создание пользователя: {}", user);
        User createdUser = userDao.save(user);
        log.info("Пользователь успешно создан: {}", createdUser);
        return createdUser;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        log.info("Получение списка пользователей");
        List<User> users = userDao.findAll();
        log.info("Получено {} пользователей", users.size());
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        log.info("Получение пользователя по идентификатору: {}", userId);
        User user = userDao.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с идентификатором: " + userId));
        log.info("Получен пользователь: {}", user);
        return user;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        log.info("Обновление пользователя: {}", user);
        if (!userDao.existsById(user.getId())) {
            throw new UserNotFoundException("Пользователь не найден с идентификатором: " + user.getId());
        }
        User updatedUser = userDao.save(user);
        log.info("Пользователь успешно обновлен: {}", updatedUser);
        return updatedUser;
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        log.info("Удаление пользователя с идентификатором: {}", userId);
        if (!userDao.existsById(userId)) {
            throw new UserNotFoundException("Пользователь не найден с идентификатором: " + userId);
        }
        userDao.deleteById(userId);
        log.info("Пользователь успешно удален");
    }
}
