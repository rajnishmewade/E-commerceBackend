package service;

import dto.ProductDTO;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Method to get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Method to get products by category
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Method to get a product by its name
    public Product getProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Product not found with name: " + name));
    }
}}
