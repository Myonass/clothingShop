package ru.tolmachev.clothingShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tolmachev.clothingShop.Models.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
