package repository;

import entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Method to find a product by its ID
    Optional<Product> findById(Long productId);

    // Method to get all products
    List<Product> findAll();

    // Method to find products by category
    List<Product> findByCategory(String category);

    // Method to check if a product with a specific name already exists
    Optional<Product> findByName(String name);
}
