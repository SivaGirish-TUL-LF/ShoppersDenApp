package com.shoppersDenApp.dao;

import com.shoppersDenApp.helpers.PostgresConnHelper;
import com.shoppersDenApp.models.Cart;
import com.shoppersDenApp.models.Customer;
import com.shoppersDenApp.models.Product;

import java.sql.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao{
    private final Connection conn;
    private Cart cart;
    private boolean flag;
    private PreparedStatement loginPrepStatement;
    private PreparedStatement registerPrepStatement;
    private PreparedStatement addressPrepStatement;
    private PreparedStatement getAddressPrepStatement;
    private ResourceBundle resourceBundle;
    private CartDao cartDao = new CartDaoImpl();

    public CustomerDaoImpl() throws SQLException{
        conn = PostgresConnHelper.getConnection();
        conn.setAutoCommit(false);
        resourceBundle = ResourceBundle.getBundle("db");
        if (conn != null)
            System.out.println("Connection Established Successfully");
        else
            System.out.println("Connection Failed");
    }

    @Override
    public void addToCart(Product product,int qty) {
        if (cart == null){
            System.out.println("Please Login !!");
            return;
        }
        // Default value : 1
        //int qty = 1;
        cartDao.addToCart(cart,product,qty);
    }

    @Override
    public void removeItemFromCart(Product product) {
        if (cart == null){
            System.out.println("Please Login !!");
            return;
        }
        cartDao.removeFromCart(cart,product);
    }


    @Override
    public void login(String email,String pwd) {
        String loginQuery = resourceBundle.getString("loginQuery");
        flag = false;
        long c_id = 0;
        try{
            loginPrepStatement = conn.prepareStatement(loginQuery);
            loginPrepStatement.setString(1,email);
            ResultSet rs = loginPrepStatement.executeQuery();

            while (rs.next()) {
                if (rs.getString("password").equals(pwd)){
                    flag = true;
                    System.out.println("Welcome " + rs.getString("name"));
                    c_id = rs.getLong("customer_id");
                    // System.out.println(c_id);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (flag) {
            cart = new Cart();
            cart.setUser_id(c_id);
            cart.setCartTable(new Hashtable<Product,Integer>(10));
            cart.setBillAmt(0.0);
        }
        else{
            System.out.println("Invalid credentials");
        }
    }

    @Override
    public void register(Customer customer) {
        String registerQuery = resourceBundle.getString("createUser");
        String addressQuery = resourceBundle.getString("createAddress");
        String getAddressIdQuery = resourceBundle.getString("getAddressId");
        try{
            registerPrepStatement = conn.prepareStatement(registerQuery);
            addressPrepStatement = conn.prepareStatement(addressQuery);
            getAddressPrepStatement = conn.prepareStatement(getAddressIdQuery);
            addressPrepStatement.setString(1,customer.getAddress().getAddress());
            addressPrepStatement.setString(2,customer.getAddress().getCity());
            addressPrepStatement.setString(3,customer.getAddress().getState());
            addressPrepStatement.setString(4,customer.getAddress().getCountry());
            addressPrepStatement.setLong(5,customer.getAddress().getZip());
            addressPrepStatement.executeUpdate();
            getAddressPrepStatement.setString(1,customer.getAddress().getAddress());
            getAddressPrepStatement.setString(2,customer.getAddress().getCity());
            getAddressPrepStatement.setString(3,customer.getAddress().getState());
            getAddressPrepStatement.setString(4,customer.getAddress().getCountry());
            getAddressPrepStatement.setLong(5,customer.getAddress().getZip());
            ResultSet rs = getAddressPrepStatement.executeQuery();
            long temp = 0;
            while (rs.next()){
                temp = rs.getLong("addr_id");
            }
            System.out.println(temp);
            registerPrepStatement.setString(1,customer.getName());
            registerPrepStatement.setLong(2,customer.getPhoneNo());
            registerPrepStatement.setString(3, customer.getEmail());
            registerPrepStatement.setLong(4,temp);
            registerPrepStatement.setString(5,customer.getPwd());
            registerPrepStatement.setString(6, customer.getSecretQ());
            registerPrepStatement.setString(7, customer.getSecretA());
            registerPrepStatement.executeUpdate();
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateCart(Product product, int qty) {
        if (!flag || cart == null){
            System.out.println("Please Login !!");
            return;
        }
        cartDao.updateCart(cart,product,qty);
    }

    @Override
    public void pay() throws SQLException {
        if (!flag){
            System.out.println("Please Login !");
            return;
        }
        double billAmt = 0;
        Hashtable<Product,Integer> ht = cart.getCartTable();
        System.out.println("Product Name \t Product Qty \t Cost");
        Product temp;
        double tCost = 0;
        for (Map.Entry<Product,Integer> m : ht.entrySet()){
            temp = m.getKey();
            tCost = m.getValue() * temp.getPrice();
            System.out.println(temp.getProd_name() + " \t " + m.getValue() + " \t " + tCost);
            billAmt+= tCost;
        }
        TransactionDao transactionDao = new TransactionDaoImpl();
        transactionDao.uploadTransaction(cart);
        System.out.println("Total Amount = " + billAmt);
    }
}
