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
    private PreparedStatement addCategoryPrepStatement;
    private PreparedStatement adminLoginPrepStatement;
    private PreparedStatement deleteProductPrepStatement;
    private ResourceBundle resourceBundle;
    private boolean loginFlag = false;
    private long admin_id = 0;
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
        if (!loginFlag){
            System.out.println("Please Login !");
            return;
        }
        String insertProduct = resourceBundle.getString("addProduct");
        try {
            addPrepStatement = conn.prepareStatement(insertProduct);
            addPrepStatement.setString(1,product.getProd_name());
            addPrepStatement.setString(2,product.getProd_desc());
            addPrepStatement.setDate(3, Date.valueOf(product.getDom()));
            addPrepStatement.setDouble(4,product.getPrice());
            addPrepStatement.setLong(5,product.getCate_id());
            addPrepStatement.executeUpdate();
        }catch (SQLException s){
            s.printStackTrace();
        }
        conn.commit();
    }

    @Override
    public void deleteProduct(int Prod_id) {
        // Cascade Delete
        if (!loginFlag){
            System.out.println("Please Login !");
            return;
        }
        String deleteProductQuery = resourceBundle.getString("deleteProduct");
        try{
            deleteProductPrepStatement = conn.prepareStatement(deleteProductQuery);
            deleteProductPrepStatement.setLong(1,Prod_id);
            deleteProductPrepStatement.executeUpdate();
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateAccount(String name,String pwd,String npwd) throws SQLException {
        if (!loginFlag){
            System.out.println("Please Login !");
            return;
        }

        String updateAccount = resourceBundle.getString("updateAccount");
        try{
            accountPrepStatement = conn.prepareStatement(updateAccount);
            accountPrepStatement.setString(1,npwd);
            accountPrepStatement.setString(2,name);
            accountPrepStatement.setString(3,pwd);
            accountPrepStatement.executeUpdate();
        }catch (SQLException s){
            s.printStackTrace();
        }
        conn.commit();
    }

    @Override
    public void addCategory(String Category_name) {
        if (!loginFlag){
            System.out.println("Please Login !");
            return;
        }
        String addCategoryQuery = resourceBundle.getString("addCategory");
        try{
            addCategoryPrepStatement = conn.prepareStatement(addCategoryQuery);
            addCategoryPrepStatement.setString(1,Category_name);
            addCategoryPrepStatement.executeUpdate();
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean login(String name, String pwd) {
        String adminLoginQuery = resourceBundle.getString("adminLogin");
        long temp;
        try{
            adminLoginPrepStatement = conn.prepareStatement(adminLoginQuery);
            adminLoginPrepStatement.setString(1,name);
            adminLoginPrepStatement.setString(2,pwd);
            ResultSet rs = adminLoginPrepStatement.executeQuery();
            while (rs.next()){
                if (rs.getString("admin_pwd").equals(pwd)) {
                    loginFlag = true;
                    System.out.println("Welcome " + rs.getString("admin_name"));
                    admin_id = rs.getLong("admin_id");
                }
                else {
                    System.out.println("Invalid Credentials");
                    return false;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return loginFlag;
    }

    @Override
    public void viewTransactions() throws SQLException {
        TransactionDao transactionDao = new TransactionDaoImpl();
        transactionDao.displayAllTransaction();
    }
}
