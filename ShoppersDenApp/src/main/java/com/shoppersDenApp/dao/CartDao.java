package com.shoppersDenApp.dao;

import com.shoppersDenApp.models.Cart;
import com.shoppersDenApp.models.Product;

public interface CartDao {
    boolean addToCart(Cart cart, Product product,int qty);
    boolean removeFromCart(Cart cart,Product product);
    boolean updateCart(Cart cart,Product product,int qty);
}
