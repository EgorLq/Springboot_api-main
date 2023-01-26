package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
public class User {
    private int User_id;
    private  String FullName ;
    private String  role ;
    private String login ;
    private String password ;

}
