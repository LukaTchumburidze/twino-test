package org.twino.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private transient String confirmPassword;
}
