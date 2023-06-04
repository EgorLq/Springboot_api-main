package com.example.demo.dto;

import lombok.*;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDTO {
    /**
     * Идентификатор пользователя.
     */
    private Long id;
    /**
     * Полное имя пользователя.
     */
    private String fullName;
    /**
     * Роль пользователя.
     */
    private String role;
    /**
     * Логин пользователя.
     */
    private String login;
    /**
     * Пароль пользователя.
     */
    private String password ;


}