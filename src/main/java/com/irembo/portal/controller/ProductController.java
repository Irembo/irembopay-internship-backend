package com.irembo.portal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.Product;
import com.irembo.portal.repository.ProductRepository;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
