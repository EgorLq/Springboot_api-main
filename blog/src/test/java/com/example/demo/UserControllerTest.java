package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.maper.UserMapper;
import com.example.demo.services.UserService;
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
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserControllerTest {
  private MockMvc mockMvc;
  private UserService userServiceMock;
  private UserMapper userMapperMock;

  @BeforeEach
  void setup() {
    userServiceMock = Mockito.mock(UserService.class);
    userMapperMock = Mockito.mock(UserMapper.class);
    UserController userController = new UserController(userServiceMock, userMapperMock);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  void testCreateUser() throws Exception {
    UserDTO userDto = createUserDtoInstance();
    User user = createUserInstance();

    when(userMapperMock.userDTOToUser(any(UserDTO.class))).thenReturn(user);
    when(userServiceMock.createUser(any(User.class))).thenReturn(user);
    when(userMapperMock.userToUserDTO(any(User.class))).thenReturn(userDto);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"fullName\":\"John Doe\",\"role\":\"user\",\"login\":\"john.doe\",\"password\":\"123123123\"}"))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("John Doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("user"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("john.doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("123123123"));
  }

  @Test
  void testGetAllUsers() throws Exception {
    List<User> users = new ArrayList<>();
    users.add(createUserInstance());

    when(userServiceMock.getAllUsers()).thenReturn(users);
    when(userMapperMock.userToUserDTO(any(User.class)))
        .thenAnswer(
            invocation -> {
              User user = invocation.getArgument(0);
              UserDTO userDto = new UserDTO();
              userDto.setId(user.getId());
              userDto.setFullName(user.getFullName());
              userDto.setRole(user.getRole());

              userDto.setPassword(user.getPassword());
              return userDto;
            });

    mockMvc
        .perform(MockMvcRequestBuilders.get("/users"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullName").value("John Doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].role").value("user"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("123123123"));
  }

  @Test
  void testGetUserById() throws Exception {
    User user = createUserInstance();

    when(userServiceMock.getUserById(1L)).thenReturn(user);
    when(userMapperMock.userToUserDTO(any(User.class)))
        .thenAnswer(
            invocation -> {
              User user1 = invocation.getArgument(0);
              UserDTO userDto = new UserDTO();
              userDto.setId(user1.getId());
              userDto.setFullName(user1.getFullName());
              userDto.setRole(user1.getRole());

              userDto.setPassword(user1.getPassword());
              return userDto;
            });

    mockMvc
        .perform(MockMvcRequestBuilders.get("/users/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("John Doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("user"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("123123123"));
  }

  @Test
  void testUpdateUser() throws Exception {
    UserDTO updatedUserDto = createUserDtoInstance();
    updatedUserDto.setRole("admin");
    updatedUserDto.setPassword("123412431412");

    User updatedUser = createUserInstance();
    updatedUser.setRole("admin");
    updatedUser.setPassword("123412431412");

    when(userMapperMock.userDTOToUser(any(UserDTO.class))).thenReturn(updatedUser);
    when(userServiceMock.updateUser(any(User.class))).thenReturn(updatedUser);
    when(userMapperMock.userToUserDTO(any(User.class))).thenReturn(updatedUserDto);

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"fullName\":\"John Doe\",\"role\":\"admin\",\"login\":\"john.doe\",\"password\":\"123412431412\"}"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("John Doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("admin"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("john.doe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("123412431412"));
  }

  @Test
  void testDeleteUser() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.delete("/users/1"))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  private UserDTO createUserDtoInstance() {
    UserDTO userDto = new UserDTO();
    userDto.setId(1L);
    userDto.setFullName("John Doe");
    userDto.setRole("user");
    userDto.setLogin("john.doe");
    userDto.setPassword("123123123");
    return userDto;
  }

  private User createUserInstance() {
    User user = new User();
    user.setId(1L);
    user.setFullName("John Doe");
    user.setRole("user");

    user.setPassword("123123123");
    return user;
  }
}