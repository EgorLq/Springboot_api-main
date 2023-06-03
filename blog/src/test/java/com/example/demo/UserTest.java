package com.example.demo;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class UserTest {

    @Test
     void testUserConstructor() {
        Long id = 1L;
        String fullName = "John Doe";
        String role = "user";
        String username = "john doe";
        String password = "password";

        User user = new User(id, fullName, role, username, password);

        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(fullName, user.getFullName());
        Assertions.assertEquals(role, user.getRole());
        Assertions.assertEquals(username, user.getUsername());
        Assertions.assertEquals(password, user.getPassword());
    }

    @Test
     void testUserSettersAndGetters() {
        User user = new User();

        Long id = 1L;
        String fullName = "John Doe";
        String role = "user";
        String username = "john doe";
        String password = "password";

        user.setId(id);
        user.setFullName(fullName);
        user.setRole(role);
        user.setUsername(username);
        user.setPassword(password);

        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(fullName, user.getFullName());
        Assertions.assertEquals(role, user.getRole());
        Assertions.assertEquals(username, user.getUsername());
        Assertions.assertEquals(password, user.getPassword());
    }

    @Test
     void testUserToString() {
        Long id = 1L;
        String fullName = "John Doe";
        String role = "user";
        String username = "john doe";
        String password = "password";

        User user = new User(id, fullName, role, username, password);

        String expectedToString = "User(id=1, fullName=John Doe, role=user, username=john doe, password=password)";
        Assertions.assertEquals(expectedToString, user.toString());
    }
}
