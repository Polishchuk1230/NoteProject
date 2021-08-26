package com.example.homeworkno38;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.PersistenceConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Data

@Entity
@Table(name="notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    @Column(name="note")
    private String description;

    @PersistenceConstructor
    public Note(int id, String title, String description) {
        this.id = id; this.title = title; this.description = description;
    }

}
