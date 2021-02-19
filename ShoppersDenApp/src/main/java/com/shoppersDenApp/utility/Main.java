package com.shoppersDenApp.utility;

import com.shoppersDenApp.dao.*;
import com.shoppersDenApp.models.Address;
import com.shoppersDenApp.models.Customer;
import com.shoppersDenApp.models.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        productDao.display();
        List<Product> productList = productDao.getAllProducts();
        //adminDao adminDao = new AdminDaoImpl();
        // create a new product

        /*
        Product product = new Product();
        product.setProd_id(1005);
        product.setProd_desc("Comfort item");
        product.setCate_id(4);
        product.setDom(LocalDate.parse("2018-12-07"));
        product.setPrice(3600.0);
        product.setImg_url("./images");
        product.setProd_name("Chair");
        */

        // add product
        //adminDao.addProdToCategory(product);

        CustomerDao customerDao = new CustomerDaoImpl();
        Customer customer = new Customer();
        customer.setEmail("Sam@gmail.com");
        customer.setName("Sam");
        customer.setPhoneNo(638722289);
        customer.setPwd("Sam123#");
        customer.setSecretA("Whale");
        customer.setSecretQ("What is my favourite animal?");
        Address addr = new Address();
        addr.setAddress("Electronic City");
        addr.setCity("Bangalore");
        addr.setState("Karnataka");
        addr.setCountry("India");
        addr.setZip(560083);
        customer.setAddress(addr);
        customerDao.register(customer);
        customerDao.login(customer.getEmail(),customer.getPwd());
        customerDao.addToCart(productList.get(2),3);
        customerDao.addToCart(productList.get(3),5);
        customerDao.updateCart(productList.get(4),6);
        //customerDao.updateCart(productList.get(3),3);
        customerDao.removeItemFromCart(productList.get(2));
        customerDao.pay();
    }
}
