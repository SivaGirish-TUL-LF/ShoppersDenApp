package com.shoppersDenApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Hashtable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private long user_id;
    private Hashtable<Product, Integer> cartTable;
    private double billAmt;

    public void insertIntoCart(Product prod,int qty){
        cartTable.put(prod,qty);
        return;
    }

    public void removeFromCart(Product prod){
        cartTable.remove(prod);
        return;
    }

    public void updateCart(Product prod,int qty){
        cartTable.put(prod,qty);
        return;
    }
}
