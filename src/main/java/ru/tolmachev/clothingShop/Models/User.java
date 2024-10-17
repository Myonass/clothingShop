package ru.tolmachev.clothingShop.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import ru.tolmachev.clothingShop.Role.UserRole;


@Data
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING) // Хранение ролей в виде строк в базе данных
    private List<UserRole> roles;

}
