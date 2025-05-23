package com.hjk.EasyManage.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Users {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pk")
    private Long id;

    private String username;

    private String password;

    private LocalDateTime createAt;

    private Role role;
}
