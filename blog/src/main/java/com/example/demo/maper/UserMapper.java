package com.example.demo.maper;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  private final ModelMapper modelMapper;

  public UserMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  /**
   * Преобразует сущность User в объект UserDTO.
   *
   * @param user сущность User для преобразования
   * @return объект UserDTO, содержащий данные из сущности User
   */
  public UserDTO userToUserDTO(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  /**
   * Преобразует объект UserDTO в сущность User.
   *
   * @param userDTO объект UserDTO для преобразования
   * @return сущность User, содержащая данные из объекта UserDTO
   */
  public User userDTOToUser(UserDTO userDTO) {
    return modelMapper.map(userDTO, User.class);
  }
}
