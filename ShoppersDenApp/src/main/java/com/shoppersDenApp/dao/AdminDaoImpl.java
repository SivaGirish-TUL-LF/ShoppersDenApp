package com.shoppersDenApp.dao;

import com.shoppersDenApp.helpers.PostgresConnHelper;
import com.shoppersDenApp.models.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminDaoImpl implements AdminDao{
    private Connection conn;
    private PreparedStatement addPrepStatement;
    private PreparedStatement accountPrepStatement;
    private PreparedStatement updatePrepStatement;
    private ResourceBundle resourceBundle;
    public AdminDaoImpl() throws SQLException {
        conn = PostgresConnHelper.getConnection();
        conn.setAutoCommit(false);
        resourceBundle = ResourceBundle.getBundle("db");
        if (conn != null)
            System.out.println("Connection Established Successfully");
        else
            System.out.println("Connection Failed");
    }
    @Override
    public void addProdToCategory(Product product) throws SQLException {
        String insertProduct = resourceBundle.getString("addProduct");
        try {
            addPrepStatement = conn.prepareStatement(insertProduct);
            addPrepStatement.setLong(1,product.getProd_id());
            addPrepStatement.setString(2,product.getProd_name());
            addPrepStatement.setString(3,product.getProd_desc());
            addPrepStatement.setString(4,product.getImg_url());
            addPrepStatement.setDate(5, Date.valueOf(product.getDom()));
            addPrepStatement.setDouble(6,product.getPrice());
            addPrepStatement.setLong(7,product.getCate_id());
            addPrepStatement.executeUpdate();
        }catch (SQLException s){
            s.printStackTrace();
        }
        conn.commit();
    }

    @Override
    public void updateAccount(String name,String pwd) throws SQLException {
        String updateAccount = resourceBundle.getString("updateProduct");
        try{
            accountPrepStatement = conn.prepareStatement(updateAccount);
            accountPrepStatement.setString(1,name);
            accountPrepStatement.setString(2,pwd);
            accountPrepStatement.executeUpdate();
        }catch (SQLException s){
            s.printStackTrace();
        }
        conn.commit();
    }

    @Override
    public void updateProdToCategory(Product product) {
    }
}
