package com.shoppersDenApp.dao;

import com.shoppersDenApp.helpers.PostgresConnHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            System.out.println("Product Id\tProduct Name\tProduct Desc\t\tDate Of Manufacture\tPrice\t\tCategory");
            System.out.println("========================================================================================");
            while (rs.next()){
                System.out.println(rs.getLong("prod_id") + "\t\t" + rs.getString("prod_name") + "\t\t\t" + rs.getString("prod_desc") + "\t\t\t" + rs.getDate("dom") + "\t\t" + rs.getDouble("price") + "\t\t" + rs.getString("category_name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
