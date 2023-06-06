package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.maper.UserMapper;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
  @Mock private UserService userServiceMock;

  @Mock private UserMapper userMapperMock;
  @InjectMocks
   private  UserController userController ;
  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
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
        .andExpect(
            MockMvcResultMatchers.content()
                .json(
                    "{\"id\":1,\"fullName\":\"John Doe\",\"role\":\"user\",\"login\":\"john.doe\",\"password\":\"123123123\"}"));
  }

  @Test
  void testGetAllUsers() throws Exception {
    List<User> users = new ArrayList<>();
    users.add(createUserInstance());
    setupUserMapperMock();
    when(userServiceMock.getAllUsers()).thenReturn(users);

    String expectedJsonArray = generateExpectedJsonArray(users);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/users"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(expectedJsonArray));
  }

  @Test
  void testGetUserById() throws Exception {
    User user = createUserInstance();
    when(userServiceMock.getUserById(1L)).thenReturn(user);
    setupUserMapperMock();
    mockMvc
        .perform(MockMvcRequestBuilders.get("/users/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content()
                .json(
                    "{\"id\":1,\"fullName\":\"John Doe\",\"role\":\"user\",\"password\":\"123123123\"}"));
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
        .andExpect(
            MockMvcResultMatchers.content()
                .json(
                    "{\"id\":1,\"fullName\":\"John Doe\",\"role\":\"admin\",\"login\":\"john.doe\",\"password\":\"123412431412\"}"));
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

  private String generateExpectedJsonArray(List<User> users) throws JsonProcessingException {
    List<String> userJsonList = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    for (User user : users) {
      String userJson = objectMapper.writeValueAsString(user);
      userJsonList.add(userJson);
    }

    return "[" + String.join(",", userJsonList) + "]";
  }

  private void setupUserMapperMock() {
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
  }
}
