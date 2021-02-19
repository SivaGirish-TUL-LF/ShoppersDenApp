package com.shoppersDenApp.dao;

import com.shoppersDenApp.models.Cart;
import com.shoppersDenApp.models.Product;

public class CartDaoImpl implements CartDao{

    @Override
    public boolean addToCart(Cart cart, Product product,int qty) {
        cart.insertIntoCart(product,qty);
        return false;
    }

    @Override
    public boolean removeFromCart(Cart cart, Product product) {
        cart.removeFromCart(product);
        return false;
    }

    @Override
    public boolean updateCart(Cart cart, Product product,int qty) {
        cart.updateCart(product,qty);
        return false;
    }
}
