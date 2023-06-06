package com.example.demo.entity;

import com.example.demo.annotation.NumericPassword;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "fullname")
  private String fullName;

  @Column(name = "role")
  private String role;

  @Column(name = "login")
  private String login;

  @NumericPassword
  @Column(name = "password")
  private String password;


}
