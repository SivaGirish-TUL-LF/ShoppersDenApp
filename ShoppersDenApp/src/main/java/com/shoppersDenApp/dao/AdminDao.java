package com.shoppersDenApp.dao;

import com.shoppersDenApp.models.Product;

import java.sql.SQLException;
import java.time.LocalDate;

public interface AdminDao {
    void addProdToCategory(Product product) throws SQLException;
    void updateAccount(String name,String pwd) throws SQLException;
    void updateProdToCategory(Product product);
}
