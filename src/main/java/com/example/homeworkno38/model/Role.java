package com.example.homeworkno38.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data

@Entity
@Table(name = "roles")
public class Role {
    //------------------------------------------------------------------------------------------------------------Fields
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    //-------------------------------------------------------------------------------------------------------Constructor
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
