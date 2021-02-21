package com.shoppersDenApp.models;

import com.shoppersDenApp.dao.ProductDao;
import com.shoppersDenApp.dao.ProductDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTest {
    ProductDao productDao = new ProductDaoImpl();
    List<Product> productList;
    public ProductTest() throws SQLException {
    }

    @BeforeEach
    public void getInstance(){
        productList = productDao.getAllProducts();
    }

    @Test
    @DisplayName("List of products must not be empty")
    public void testProductRetrieval(){
        assertTrue(productList.size() > 0);
    }

    @Test
    @DisplayName("Products cannot be null")
    public void testNotNullProduct(){
        assertTrue(productList.stream().allMatch(Objects::nonNull));
    }
}
