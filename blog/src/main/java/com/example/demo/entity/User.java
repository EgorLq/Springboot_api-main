package com.example.demo.entity;



import com.example.demo.annotation.NumericPassword;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "fullname")
    private String fullName;

    private String role;
    @NotEmpty
    @Column(name = "login")
    private String username;
    @NotEmpty
    @NumericPassword
    private String password;
}