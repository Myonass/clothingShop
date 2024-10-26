package ru.tolmachev.clothingShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tolmachev.clothingShop.Models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}