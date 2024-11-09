package controller;

import dto.ProductDTO;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.List;

public class ProductController {

    @RestController
    @RequestMapping("/products")
    public class ProductController {

        @Autowired
        private ProductService productService;

        @PostMapping("/addproduct")
        public Product addProduct(@RequestBody ProductDTO productDTO) {
            return productService.addProduct(productDTO);
        }

        @PutMapping("/updateproduct/{productId}")
        public Product updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
            return productService.updateProduct(productId, productDTO);
        }

        @DeleteMapping("/deleteproduct/{productId}")
        public String deleteProduct(@PathVariable Long productId) {
            productService.deleteProduct(productId);
            return "Product deleted successfully";
        }

        @GetMapping("/")
        public List<Product> getAllProducts() {
            return productService.getAllProducts();
        }
    }
}
