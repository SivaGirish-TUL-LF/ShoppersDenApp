package com.shoppersDenApp.dao;

import com.shoppersDenApp.models.Product;

import java.util.List;

public interface ProductDao {
    void display();
    List<Product> getAllProducts();
}
