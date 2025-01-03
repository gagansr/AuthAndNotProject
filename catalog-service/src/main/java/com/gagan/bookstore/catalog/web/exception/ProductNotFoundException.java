package com.gagan.bookstore.catalog.web.exception;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String message) {
        super(message);
    }
    public static ProductNotFoundException forCode(String code){
        return new ProductNotFoundException("Product with code " + code + " not found");
    }
}