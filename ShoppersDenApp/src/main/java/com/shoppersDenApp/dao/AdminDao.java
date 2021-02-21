package com.shoppersDenApp.dao;

import com.shoppersDenApp.models.Product;
import com.shoppersDenApp.models.Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AdminDao {
    void addProdToCategory(Product product) throws SQLException;
    void deleteProduct(int Prod_id);
    void updateAccount(String name,String pwd,String npwd) throws SQLException;
    void addCategory(String Category_name);
    boolean login(String name,String pwd);
    void viewTransactions() throws SQLException;
    List<Transaction> getAllTransactions() throws SQLException;
}
