package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "diary")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "times")
    private Time times;

    @Column(name = "cases")
    private String cases;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "dates")
    private Date dates;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Diary)) {
            return false;
        }
        Diary diary = (Diary) o;
        return getId() == diary.getId();
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(getId());
    }
}
