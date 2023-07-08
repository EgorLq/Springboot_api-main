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
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Column(name = "dates")
  private Date dates;

}
