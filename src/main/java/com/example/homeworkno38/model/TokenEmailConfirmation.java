package com.example.homeworkno38.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data

@Entity
public class TokenEmailConfirmation {
    //------------------------------------------------------------------------------------------------------------Fields
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String value;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    //-------------------------------------------------------------------------------------------------------Constructor
    public TokenEmailConfirmation(User user) {
        this.user = user;

        this.value = java.util.UUID.randomUUID().toString();
    }
}
