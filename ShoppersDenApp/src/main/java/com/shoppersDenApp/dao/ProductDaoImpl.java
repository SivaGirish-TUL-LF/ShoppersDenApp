package com.shoppersDenApp.dao;


import com.shoppersDenApp.helpers.PostgresConnHelper;
import com.shoppersDenApp.models.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ProductDaoImpl implements ProductDao{
    private Connection conn;
    private Statement statement;
    private ResourceBundle resourceBundle;
    public ProductDaoImpl() throws SQLException {
        conn = PostgresConnHelper.getConnection();
        conn.setAutoCommit(false);
        resourceBundle = ResourceBundle.getBundle("db");
        if (conn != null)
            System.out.println("Connection Established Successfully");
        else
            System.out.println("Connection Failed");
    }
    @Override
    public void display() {
        String displayProducts = resourceBundle.getString("displayProducts");
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(displayProducts);
            System.out.println("Serial No\tProduct Id\tProduct Name\tProduct Desc\t\tDate Of Manufacture\tPrice\t\tCategory");
            System.out.println("========================================================================================");
            int k = 0;
            while (rs.next()){
                k++;
                System.out.println(k + "\t" + rs.getLong("prod_id") + "\t\t" + rs.getString("prod_name") + "\t\t\t" + rs.getString("prod_desc") + "\t\t\t" + rs.getDate("dom") + "\t\t" + rs.getDouble("price") + "\t\t" + rs.getString("category_name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<Product>(10);
        String displayProducts = resourceBundle.getString("displayProducts");
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(displayProducts);
            Product product;
            //DateHelper dh;
            while (rs.next()){
                product = new Product();
                product.setProd_id(rs.getLong("prod_id"));
                product.setProd_desc(rs.getString("prod_desc"));
                product.setCate_id(4);
                product.setDom(rs.getDate("dom").toLocalDate());
                product.setPrice(rs.getDouble("price"));
                product.setImg_url("./images");
                product.setProd_name(rs.getString("prod_name"));
                productList.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }
}
