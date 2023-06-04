package com.example.demo.dto;

import com.example.demo.annotation.TimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.sql.Time;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class DiaryDTO {
  private Long id;
  private UserDTO user;
  @JsonDeserialize(using = TimeDeserializer.class)
  private Time times;
  private String cases;


}