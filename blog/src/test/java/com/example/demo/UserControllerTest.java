package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.entity.User;
import com.example.demo.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

class UserControllerTest {

  @Test
  void testCreateUser() throws Exception {
    UserServiceImpl userServiceImplMock = Mockito.mock(UserServiceImpl.class);
    UserController userController = new UserController(userServiceImplMock);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    User user = new User();
    user.setId(1L);
    user.setFullName("John Doe");
    user.setRole("user");
    user.setUsername("john doe");
    user.setPassword("123123123");

    Mockito.when(userServiceImplMock.createUser(Mockito.any(User.class))).thenReturn(user);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"fullName\":\"John Doe\",\"role\":\"user\",\"username\":\"john doe\",\"123123123\":\"123123123\"}"))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("John Doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("user"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("john doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("123123123"));
  }

  @Test
  void testGetAllUsers() throws Exception {
    UserServiceImpl userServiceImplMock = Mockito.mock(UserServiceImpl.class);
    UserController userController = new UserController(userServiceImplMock);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    User user1 = new User();
    user1.setId(1L);
    user1.setFullName("John Doe");
    user1.setRole("user");
    user1.setUsername("john doe");
    user1.setPassword("123123123");

    User user2 = new User();
    user2.setId(2L);
    user2.setFullName("Jane Smith");
    user2.setRole("admin");
    user2.setUsername("anesthesia");
    user2.setPassword("123123123");

    List<User> users = new ArrayList<>();
    users.add(user1);
    users.add(user2);

    Mockito.when(userServiceImplMock.getAllUsers()).thenReturn(users);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/users"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullName").value("John Doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].role").value("user"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("john doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("123123123"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].fullName").value("Jane Smith"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].role").value("admin"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].username").value("anesthesia"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].password").value("123123123"));
  }

  @Test
  void testUpdateUser() throws Exception {
    UserServiceImpl userServiceImplMock = Mockito.mock(UserServiceImpl.class);
    UserController userController = new UserController(userServiceImplMock);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    User existingUser = new User();
    existingUser.setId(1L);
    existingUser.setFullName("John Doe");
    existingUser.setRole("user");
    existingUser.setUsername("john doe");
    existingUser.setPassword("1234124");

    User updatedUser = new User();
    updatedUser.setId(1L);
    updatedUser.setFullName("John Doe");
    updatedUser.setRole("admin");
    updatedUser.setUsername("john doe");
    updatedUser.setPassword("123412431412");

    Mockito.when(userServiceImplMock.updateUser(Mockito.any(User.class))).thenReturn(updatedUser);

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"fullName\":\"John Doe\",\"role\":\"admin\",\"username\":\"john doe\",\"1234124\":\"123412431412\"}"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("John Doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("admin"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("john doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("123412431412"));
  }

  @Test
  void testDeleteUser() throws Exception {
    UserServiceImpl userServiceImplMock = Mockito.mock(UserServiceImpl.class);
    UserController userController = new UserController(userServiceImplMock);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    User user = new User();
    user.setId(1L);
    user.setFullName("John Doe");
    user.setRole("user");
    user.setUsername("john doe");
    user.setPassword("1234124");

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/users/1"))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }
}
