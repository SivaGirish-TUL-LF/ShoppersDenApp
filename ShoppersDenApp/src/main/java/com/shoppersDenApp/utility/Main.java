package com.shoppersDenApp.utility;

import com.shoppersDenApp.dao.AdminDao;
import com.shoppersDenApp.dao.AdminDaoImpl;
import com.shoppersDenApp.dao.ProductDao;
import com.shoppersDenApp.dao.ProductDaoImpl;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        // ProductDao productDao = new ProductDaoImpl();
        // productDao.display();
        AdminDao adminDao = new AdminDaoImpl();
        adminDao.addProdToCategory(1003,"bat","Leisure item","./images", LocalDate.parse("2018-12-06"),1436.75,3);
    }
}
