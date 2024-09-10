package com.mensal.project.entities;

import com.mensal.project.entities.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "users_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(name = "user_password", nullable = false)
    private String password;
    @Column(name = "phone", unique = true, nullable = false)
    private String phone;
    @Column(name = "user_name", nullable = false)
    private String name;


    @Enumerated(EnumType.STRING) @Column(name = "user_type", nullable = false)
    private UserType userType = UserType.PARTICIPANT;

    @OneToMany(mappedBy = "users")
    private List<UserEvent> usersEvents;


}
