package com.example.demo.dto;

import com.example.demo.annotation.TimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.sql.Time;
@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class DiaryDTO {
  private int id;
  private UserDTO user;
  @JsonDeserialize(using = TimeDeserializer.class)
  private Time times;
  private String cases;

  public DiaryDTO() {
    // Пустой конструктор необходим для целей десериализации.
    // Реализация пустого конструктора позволяет фреймворкам, таким как Jackson,
    // создавать экземпляр этого класса и заполнять его поля, используя сеттеры или рефлексию.
  }
}