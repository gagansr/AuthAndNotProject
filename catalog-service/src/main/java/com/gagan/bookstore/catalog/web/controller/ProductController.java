package com.gagan.bookstore.catalog.web.controller;

import com.gagan.bookstore.catalog.domain.PagedResult;
import com.gagan.bookstore.catalog.domain.Product;
import com.gagan.bookstore.catalog.domain.ProductService;
import com.gagan.bookstore.catalog.web.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public PagedResult<Product> getAllProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getAllProducts(pageNo);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Product> getProductByCode(@PathVariable String code) throws ProductNotFoundException {
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
