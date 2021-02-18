package com.shoppersDenApp.dao;

import com.shoppersDenApp.helpers.PostgresConnHelper;

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
    public boolean addProdToCategory(long prod_id, String prod_name, String prod_desc, String img_url,LocalDate dom, double price, long cate_id) throws SQLException {
        String insertProduct = resourceBundle.getString("addProduct");
        try {
            addPrepStatement = conn.prepareStatement(insertProduct);
            addPrepStatement.setLong(1,prod_id);
            addPrepStatement.setString(2,prod_name);
            addPrepStatement.setString(3,prod_desc);
            addPrepStatement.setString(4,img_url);
            addPrepStatement.setDate(5, Date.valueOf(dom));
            addPrepStatement.setDouble(6,price);
            addPrepStatement.setLong(7,cate_id);
            addPrepStatement.executeUpdate();
        }catch (SQLException s){
            s.printStackTrace();
        }
        conn.commit();
        return false;
    }

    @Override
    public boolean updateAccount() {
        return false;
    }

    @Override
    public boolean updateProdToCategory() {
        return false;
    }
}
