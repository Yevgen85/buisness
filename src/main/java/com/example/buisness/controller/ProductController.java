package com.example.buisness.controller;

import com.example.buisness.beans.Product;
import com.example.buisness.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/{businessId}")
    public Product add(@RequestBody Product product, @PathVariable int businessId) {
        return productService.add(product, businessId);
    }

    @GetMapping
    public List<Product> getSortedByPriceDesc() {
        return productService.getOrderedDesc();
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable int productId) {
        productService.delete(productId);
    }
}
