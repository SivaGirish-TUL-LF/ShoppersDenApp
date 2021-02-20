package com.shoppersDenApp.utility;

import com.shoppersDenApp.dao.AdminDao;
import com.shoppersDenApp.dao.AdminDaoImpl;
import com.shoppersDenApp.models.Product;

import java.sql.SQLException;
import java.time.LocalDate;

public class AdminMain {
    public static void main(String[] args) throws SQLException {

        AdminDao adminDao = new AdminDaoImpl();
        adminDao.login("Dan","Dan123#");

        //adminDao.addCategory("Winter Wear");

        Product product = new Product();
        product.setProd_desc("Cold Weather item");
        product.setCate_id(10);
        product.setDom(LocalDate.parse("2018-12-07"));
        product.setPrice(3600.0);
        product.setProd_name("Pullover");

        adminDao.addProdToCategory(product);

        adminDao.viewTransactions();

    }
}
