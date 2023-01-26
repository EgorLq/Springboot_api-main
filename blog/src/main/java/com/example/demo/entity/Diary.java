package com.example.demo.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;

@Entity
@Getter
@Setter
@ToString
public class Diary {

    @Id
    @GeneratedValue
    private int id;


    private Time times;

    private String cases;

    @ManyToOne
    @JoinColumn(name="User_id")
    private User users;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Diary diary)) {
            return false;
        }

        return getId() == diary.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
