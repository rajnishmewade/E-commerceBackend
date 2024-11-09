package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class OrderController {

    @RestController
    @RequestMapping("/orders")
    public class OrderController {

        @Autowired
        private OrderService orderService;

        @PostMapping("/placeorder")
        public Order placeOrder(@RequestParam Long userId, @RequestParam String shippingAddress) {
            return orderService.placeOrder(userId, shippingAddress);
        }

        @GetMapping("/getallorders")
        public List<Order> getAllOrders() {
            return orderService.getAllOrders();
        }

        @GetMapping("/customer/{customerId}")
        public List<Order> getOrdersByCustomer(@PathVariable Long customerId) {
            return orderService.getOrdersByCustomer(customerId);
        }
    }
}
