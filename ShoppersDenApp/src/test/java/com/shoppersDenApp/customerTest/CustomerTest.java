package com.shoppersDenApp.customerTest;


import com.shoppersDenApp.dao.CustomerDao;
import com.shoppersDenApp.dao.CustomerDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class CustomerTest {
    CustomerDao customerDao;

    @BeforeEach
    public void init() throws SQLException {
        customerDao = new CustomerDaoImpl();
    }

    @Test
    @DisplayName("Login Successful?")
    public void loginTest(){
        assertTrue(customerDao.login("Sam@gmail.com","Sam123#"));
    }

}
