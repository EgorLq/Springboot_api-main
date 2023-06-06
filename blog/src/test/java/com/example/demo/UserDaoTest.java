package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles(value = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserDaoTest {

  @Autowired
  private UserRepository userRepository;

  private User createUser() {
    return new User(1L, "John Doe", "user", "johndoe", "123456");
  }

  @Test
  void testSaveUser() {
    User savedUser = userRepository.findById(1L).orElse(null);
    Assertions.assertNotNull(savedUser);
    Assertions.assertEquals(createUser(), savedUser);
  }

  @Test
  void testFindAllUsers() {
    List<User> userList = userRepository.findAll();
    Assertions.assertEquals(2, userList.size());
  }

  @Test
  void testFindUserById() {
    User foundUser = userRepository.findById(1L).orElse(null);
    Assertions.assertNotNull(foundUser);
    Assertions.assertEquals(createUser(), foundUser);
  }

  @Test
  void testExistsUserById() {
    boolean exists = userRepository.existsById(1L);
    Assertions.assertTrue(exists);
  }

  @Test
  void testDeleteUserById() {
    userRepository.deleteById(1L);
    boolean exists = userRepository.existsById(1L);
    Assertions.assertFalse(exists);
  }
}
