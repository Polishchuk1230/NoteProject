package com.example.homeworkno38.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Data

@Entity
@Table(name = "users")
public class User {
    //------------------------------------------------------------------------------------------------------------Fields
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private boolean enabled;//По дефолту false (не null, т.к. примитивный тип)

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
    @OrderBy("lastEditTime DESC")
    private List<Note> notes = new LinkedList<>();

    //-------------------------------------------------------------------------------------------------------Constructor
    public User(int id, String username, String password, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }


}
