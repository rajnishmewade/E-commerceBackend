package service;

import dto.CartDTO;
import entity.Cart;
import entity.Product;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CartRepository;
import repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    // Add a product to the cart
    public CartDTO addProductToCart(User user, Long productId, int quantity) {
        // Check if the product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the user already has the product in their cart
        Optional<Cart> existingCartItem = cartRepository.findByUserAndProduct(user, product);

        if (((Optional<?>) existingCartItem).isPresent()) {
            // If product is already in the cart, update the quantity
            Cart cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartRepository.save(cartItem);
        } else {
            // Otherwise, create a new cart item
            Cart newCartItem = new Cart();
            newCartItem.setUser(user);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            cartRepository.save(newCartItem);
        }

        return convertToCartDTO(user);
    }

    // Update quantity of a product in the cart
    public CartDTO updateCart(Long cartId, int quantity) {
        Cart cartItem = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (quantity <= 0) {
            cartRepository.delete(cartItem);
            return convertToCartDTO(cartItem.getUser());
        }

        cartItem.setQuantity(quantity);
        cartRepository.save(cartItem);

        return convertToCartDTO(cartItem.getUser());
    }

    // Remove a product from the cart
    public void removeProductFromCart(Long cartId) {
        Cart cartItem = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartRepository.delete(cartItem);
    }

    // Get all items in the user's cart
    public List<CartDTO> getCartItems(User user) {
        List<Cart> cartItems = cartRepository.findByUser(user);
        return convertToCartDTOList(cartItems);
    }

    // Convert Cart entity to CartDTO for returning API response
    private CartDTO convertToCartDTO(User user) {
        List<Cart> cartItems = cartRepository.findByUser(user);
        double totalAmount = 0;

        for (Cart cartItem : cartItems) {
            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        CartDTO cartDTO = new CartDTO();
        cartDTO.setItems(cartItems);
        cartDTO.setTotalAmount(totalAmount);
        return cartDTO;
    }

    // Convert list of Cart entities to list of CartDTOs
    private List<CartDTO> convertToCartDTOList(List<Cart> cartItems) {
        double totalAmount = 0;
        for (Cart cartItem : cartItems) {
            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        CartDTO cartDTO = new CartDTO();
        cartDTO.setItems(cartItems);
        cartDTO.setTotalAmount(totalAmount);

        return List.of(cartDTO);  // Assuming only one cart DTO for now
    }
}