package com.example.buisness.service;

import com.example.buisness.beans.Business;
import com.example.buisness.beans.Product;
import com.example.buisness.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BusinessService businessService;

    public Product add(Product product, int businessId) {
        product.setBusiness(businessService.getSingle(businessId));
        return productRepository.save(product);
    }

    public List<Product> getOrderedDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    public void delete(int productId) {
        productRepository.deleteById(productId);
    }

}

