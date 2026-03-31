package com.example.productapp.service;

import com.example.productapp.model.CartItem;
import com.example.productapp.model.Order;
import com.example.productapp.model.OrderDetail;
import com.example.productapp.repository.OrderDetailRepository;
import com.example.productapp.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartService cartService;

    @Transactional
    public Order checkout(String customerName, HttpSession session) {
        List<CartItem> cartItems = cartService.getCartItems(session);
        if (cartItems.isEmpty()) {
            return null;
        }

        double totalAmount = cartService.getTotalPrice(session);

        Order order = new Order();
        order.setCustomerName(customerName);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        
        // Save order first to get ID
        Order savedOrder = orderRepository.save(order);

        List<OrderDetail> details = new ArrayList<>();
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            detail.setPrice(item.getProduct().getPrice());
            details.add(detail);
        }

        orderDetailRepository.saveAll(details);
        savedOrder.setOrderDetails(details);

        // Clear cart after checkout
        cartService.clearCart(session);

        return savedOrder;
    }
}
