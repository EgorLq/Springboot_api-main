package com.example.demo.dto;

import com.example.demo.annotation.TimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.sql.Time;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class DiaryDTO {
  /**
   * Идентификатор дневника.
   */
  private Long id;
  /**
   * Пользователь, связанный с дневником.
   */
  private UserDTO user;
  /**
   * Время записи в дневнике.
   * Аннотация @JsonDeserialize указывает на необходимость использования кастомного десериализатора для преобразования времени.
   */
  @JsonDeserialize(using = TimeDeserializer.class)
  private Time times;
  /**
   * Описание случая/события в дневнике.
   */
  private String cases;


}