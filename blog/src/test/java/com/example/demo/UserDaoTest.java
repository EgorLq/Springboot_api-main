package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;


@DataJpaTest
@ActiveProfiles(value = "test")
class UserDaoTest {

   @Autowired
   private TestEntityManager entityManager;

   @Autowired
   private UserRepository userRepository;

   private UserDao userDao;

   @BeforeEach
   public void setUp() {
      userDao = new UserDao(userRepository);
   }

   @Test
   void testSaveUser() {
      User user = new User(null, "John Doe", "user", "johndoe", "123456");
      User savedUser = userDao.save(user);

      Assertions.assertNotNull(savedUser.getId());
      Assertions.assertEquals(user.getFullName(), savedUser.getFullName());
      Assertions.assertEquals(user.getRole(), savedUser.getRole());
      Assertions.assertEquals(user.getUsername(), savedUser.getUsername());
      Assertions.assertEquals(user.getPassword(), savedUser.getPassword());
   }

   @Test
   void testFindUserById() {
      User user = new User(null, "John Doe", "user", "johndoe", "123456");

      entityManager.persist(user);
      entityManager.flush();

      Optional<User> foundUser = userDao.findById(user.getId());

      Assertions.assertTrue(foundUser.isPresent());
      Assertions.assertEquals(user, foundUser.get());
   }



   @Test
   void testDeleteUserById() {
      User user = new User(null, "John Doe", "user", "johndoe", "123456");

      entityManager.persist(user);
      entityManager.flush();

      userDao.deleteById(user.getId());

      Optional<User> deletedUser = userRepository.findById(user.getId());

      Assertions.assertFalse(deletedUser.isPresent());
   }
}
