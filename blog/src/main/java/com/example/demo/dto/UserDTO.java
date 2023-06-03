package com.example.demo.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO {
    private int id;
    private String fullName;
    private String role;
    private String login;
    private String password ;

    public UserDTO() {
        // Пустой конструктор необходим для целей десериализации.
        // Реализация пустого конструктора позволяет фреймворкам, таким как Jackson,
        // создавать экземпляр этого класса и заполнять его поля, используя сеттеры или рефлексию.
    }
}