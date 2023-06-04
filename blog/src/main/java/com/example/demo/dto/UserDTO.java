package com.example.demo.dto;

import lombok.*;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private String role;
    private String login;
    private String password ;


}