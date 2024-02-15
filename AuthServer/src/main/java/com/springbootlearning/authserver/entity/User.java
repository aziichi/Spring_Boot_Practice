package com.springbootlearning.authserver.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;

    @Column(length = 60)
    private String password;
    private String role;
    private boolean enabled=false;
}
