package dto;

import entity.Cart;

import java.util.List;

public class CartDTO {
    public class CartDTO {
        private List<Cart> items; // List of Cart items
        private double totalAmount; // Total cart value

        // Getters and Setters

        public List<Cart> getItems() {
            return items;
        }

        public void setItems(List<Cart> items) {
            this.items = items;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }
    }
}
