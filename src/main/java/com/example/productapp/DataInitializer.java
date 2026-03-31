package com.example.productapp;

import com.example.productapp.model.Category;
import com.example.productapp.model.Product;
import com.example.productapp.repository.CategoryRepository;
import com.example.productapp.repository.OrderDetailRepository;
import com.example.productapp.repository.OrderRepository;
import com.example.productapp.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(CategoryRepository categoryRepository, 
                            ProductRepository productRepository,
                            OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository) {
        return args -> {
            // Delete in correct order to avoid FK constraints
            orderDetailRepository.deleteAll();
            orderRepository.deleteAll();
            productRepository.deleteAll();
            categoryRepository.deleteAll();

            Category laptop = new Category();
            laptop.setName("Laptop");

            Category phone = new Category();
            phone.setName("\u0110i\u1ec7n tho\u1ea1i");

            categoryRepository.saveAll(Arrays.asList(laptop, phone));

            Product p1 = new Product();
            p1.setName("Lenovo ThinkPad T15 Laptop (Intel i7, 16GB RAM, 512GB SSD)");
            p1.setPrice(27000.0);
            p1.setImage("https://images.unsplash.com/photo-1588872657578-7efd1f1555ed?auto=format&fit=crop&w=500&q=80");
            p1.setCategory(laptop);

            Product p2 = new Product();
            p2.setName("iPhone 16 Pro Max 1TB");
            p2.setPrice(41990.0);
            p2.setImage("https://images.unsplash.com/photo-1727281081623-1aa8d6848303?auto=format&fit=crop&w=500&q=80");
            p2.setCategory(phone);

            Product p3 = new Product();
            p3.setName("MacBook Air M3");
            p3.setPrice(32990.0);
            p3.setImage("https://images.unsplash.com/photo-1517336714731-489689fd1ca8?auto=format&fit=crop&w=500&q=80");
            p3.setCategory(laptop);

            Product p4 = new Product();
            p4.setName("Samsung Galaxy S24 Ultra");
            p4.setPrice(33990.0);
            p4.setImage("https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?auto=format&fit=crop&w=500&q=80");
            p4.setCategory(phone);

            Product p5 = new Product();
            p5.setName("Dell XPS 13 Plus");
            p5.setPrice(35000.0);
            p5.setImage("https://images.unsplash.com/photo-1593642632823-8f785ba67e45?auto=format&fit=crop&w=500&q=80");
            p5.setCategory(laptop);

            Product p6 = new Product();
            p6.setName("Google Pixel 9 Pro");
            p6.setPrice(21990.0);
            p6.setImage("https://images.unsplash.com/photo-1598327105666-5b89351aff97?auto=format&fit=crop&w=500&q=80");
            p6.setCategory(phone);

            productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
        };
    }
}
