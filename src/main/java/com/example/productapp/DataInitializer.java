package com.example.productapp;

import com.example.productapp.model.Category;
import com.example.productapp.model.Product;
import com.example.productapp.repository.CategoryRepository;
import com.example.productapp.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(CategoryRepository categoryRepository, ProductRepository productRepository) {
        return args -> {
            Category laptop = new Category();
            laptop.setName("Laptop");
            
            Category phone = new Category();
            phone.setName("Điện thoại");
            
            categoryRepository.saveAll(Arrays.asList(laptop, phone));

            Product p1 = new Product();
            p1.setName("Lenovo ThinkPad T15 15.6\" Laptop Intel Core i7-10610U 512GB SSD 16GB RAM FHD");
            p1.setPrice(27000.0);
            p1.setImage("https://p2-ofp.static.pub/fes/cms/2020/07/28/vclj1j7e0j6g0h0k8p7j8l0x.png");
            p1.setCategory(laptop);

            Product p2 = new Product();
            p2.setName("iPhone 16 Pro Max 1TB");
            p2.setPrice(41990.0);
            p2.setImage("https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-16-pro-model-unselect-gallery-2-202409_GEO_US?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1725592864441");
            p2.setCategory(phone);

            productRepository.saveAll(Arrays.asList(p1, p2));
        };
    }
}
