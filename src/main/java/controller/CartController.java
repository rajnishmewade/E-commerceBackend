package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class CartController {

    @RestController
    @RequestMapping("/cart")
    public class CartController {

        @Autowired
        private CartService cartService;

        @PostMapping("/add")
        public CartDTO addProductToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
            User user = new User();
            user.setId(userId);
            return cartService.addProductToCart(user, productId, quantity);
        }

        @PutMapping("/update")
        public CartDTO updateCart(@RequestParam Long cartId, @RequestParam int quantity) {
            return cartService.updateCart(cartId, quantity);
        }

        @DeleteMapping("/delete")
        public String removeProductFromCart(@RequestParam Long cartId) {
            cartService.removeProductFromCart(cartId);
            return "Product removed from cart";
        }

        @GetMapping("/")
        public List<CartDTO> getCart(@RequestParam Long userId) {
            User user = new User();
            user.setId(userId);
            return cartService.getCartItems(user);
        }
    }
}
