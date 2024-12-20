package ru.tolmachev.clothingShop.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tolmachev.clothingShop.Models.User;
import ru.tolmachev.clothingShop.Repository.UserRepository;
import ru.tolmachev.clothingShop.Role.UserRole;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user, boolean isAdmin) {
        System.out.println("Регистрация пользователя: " + user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword())); //

        if (isAdmin) {
            user.setRoles(List.of(UserRole.ROLE_ADMIN));
        } else {
            user.setRoles(List.of(UserRole.ROLE_USER));
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already taken");
        }

        User savedUser = userRepository.save(user);
        System.out.println("Пользователь сохранен: " + savedUser.getId());
        return savedUser;
    }
}