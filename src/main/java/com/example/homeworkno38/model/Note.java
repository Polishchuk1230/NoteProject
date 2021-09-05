package com.example.homeworkno38.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Data

@Entity
@Table(name="notes")
public class Note {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
    private String title;
    @Column(name="note") private String description;
    private Date lastEditTime;
}
