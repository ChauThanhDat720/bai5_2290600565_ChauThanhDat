package com.example.productapp.service;

import com.example.productapp.model.CartItem;
import com.example.productapp.model.Product;
import com.example.productapp.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    private static final String CART_SESSION_KEY = "cart";

    public List<CartItem> getCartItems(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    public void addToCart(Long productId, HttpSession session) {
        List<CartItem> cart = getCartItems(session);
        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {
            for (CartItem item : cart) {
                if (item.getProduct().getId().equals(productId)) {
                    item.setQuantity(item.getQuantity() + 1);
                    return;
                }
            }
            cart.add(new CartItem(product, 1));
        }
    }

    public double getTotalPrice(HttpSession session) {
        List<CartItem> cart = getCartItems(session);
        return cart.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void removeFromCart(Long productId, HttpSession session) {
        List<CartItem> cart = getCartItems(session);
        cart.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }
}
