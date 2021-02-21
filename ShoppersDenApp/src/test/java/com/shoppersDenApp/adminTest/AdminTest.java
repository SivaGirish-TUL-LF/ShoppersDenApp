package com.shoppersDenApp.adminTest;

import com.shoppersDenApp.dao.AdminDao;
import com.shoppersDenApp.dao.AdminDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminTest {
    AdminDao adminDao;

    @BeforeEach
    public void init() throws SQLException {
        adminDao = new AdminDaoImpl();
    }

    @Test
    @DisplayName("Login Successful?")
    public void adminLoginTest(){
        assertTrue(adminDao.login("Dan","Dan123#"));
    }

}
