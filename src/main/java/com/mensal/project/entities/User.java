package com.mensal.project.entities;

import com.mensal.project.entities.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true,nullable = false)
    private String username;
    @Column(name = "user_password", nullable = false)
    private String password;
    @Column(name = "phone", unique = true, nullable = false)
    private String phone;
    @Column(name = "user_name", nullable = false)
    private String name;


    @Enumerated(EnumType.STRING) @Column(name = "user_type", nullable = false)
    private UserType userType;

    @OneToMany(mappedBy = "users")
    private List<UserEvent> usersEvents;


}
