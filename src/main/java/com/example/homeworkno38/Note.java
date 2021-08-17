package com.example.homeworkno38;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("notes")
@NoArgsConstructor
@Data
public class Note {
    @Id
    private int id;
    private String title;
    @Column("note")
    private String description;

    public Note(String title, String description) {
        this.title = title; this.description = description;
    }

    @PersistenceConstructor
    public Note(int id, String title, String description) {
        this.id = id; this.title = title; this.description = description;
    }
}
