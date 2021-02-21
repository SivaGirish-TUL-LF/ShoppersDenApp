package com.shoppersDenApp.dao;

import com.shoppersDenApp.models.Cart;
import com.shoppersDenApp.models.Customer;
import com.shoppersDenApp.models.Transaction;

import java.util.List;

public interface TransactionDao {
    void displayTransactions(Customer customer);
    void displayAllTransaction();
    void uploadTransaction(Cart cart);
    List<Transaction> sendTransactionList();
}
