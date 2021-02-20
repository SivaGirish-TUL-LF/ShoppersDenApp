package com.shoppersDenApp.dao;

import com.shoppersDenApp.models.Cart;
import com.shoppersDenApp.models.Customer;

public interface TransactionDao {
    void displayTransactions(Customer customer);
    void displayAllTransaction();
    void uploadTransaction(Cart cart);
}
