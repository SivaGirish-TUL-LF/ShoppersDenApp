package com.shoppersDenApp.dao;

import com.shoppersDenApp.models.Cart;
import com.shoppersDenApp.models.Customer;
import com.shoppersDenApp.models.Product;

import java.sql.SQLException;

public interface CustomerDao {
    void addToCart(Product product,int qty);
    void removeItemFromCart(Product product);
    boolean login(String email,String pwd);
    void register(Customer customer);
    void updateCart(Product product,int qty);
    void pay() throws SQLException;
}
