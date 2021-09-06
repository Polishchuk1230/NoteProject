package com.example.homeworkno38.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="notes")
public class Note {
    //------------------------------------------------------------------------------------------------------------Fields
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(name="note")
    private String description;

    private Date lastEditTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
