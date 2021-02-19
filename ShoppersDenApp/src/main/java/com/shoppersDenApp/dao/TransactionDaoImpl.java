package com.shoppersDenApp.dao;

import com.shoppersDenApp.helpers.PostgresConnHelper;
import com.shoppersDenApp.models.Cart;
import com.shoppersDenApp.models.Customer;
import com.shoppersDenApp.models.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Map;
import java.util.ResourceBundle;

public class TransactionDaoImpl implements TransactionDao{
    private Connection conn;
    private ResourceBundle resourceBundle;
    private PreparedStatement transactionPrepStatement;
    private PreparedStatement transactionUploadPrepStatement;

    public TransactionDaoImpl() throws SQLException {
        conn = PostgresConnHelper.getConnection();
        conn.setAutoCommit(false);
        resourceBundle = ResourceBundle.getBundle("db");
        if (conn != null)
            System.out.println("Connection Established Successfully");
        else
            System.out.println("Connection Failed");

    }

    @Override
    public void displayTransactions(Customer customer) {
        String transactionQuery = resourceBundle.getString("displayTransactions");
        try{
            transactionPrepStatement = conn.prepareStatement(transactionQuery);
            transactionPrepStatement.setLong(1,customer.getCustomer_id());
            transactionPrepStatement.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet res = transactionPrepStatement.executeQuery();
            System.out.println("Product Id /t Product Name /t Qty Purchased /t Cost /t Date Of Purchase");
            System.out.println("========================================================================================");
            while (res.next()){
                System.out.println(res.getString("prod_id") + " /t " + res.getString("prod_name") + " /t " + res.getInt("qty") + " /t " + res.getDouble("cost") + " /t " + res.getDate("dop"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void uploadTransaction(Cart cart) {
        String transactionUploadQuery = resourceBundle.getString("uploadTransaction");
        Hashtable<Product,Integer> ht = cart.getCartTable();
        Product prod;
        try{
            transactionUploadPrepStatement = conn.prepareStatement(transactionUploadQuery);
            for (Map.Entry<Product,Integer> m : ht.entrySet()) {
                prod = m.getKey();
                transactionUploadPrepStatement.setLong(1,prod.getProd_id());
                transactionUploadPrepStatement.setLong(2,m.getValue());
                transactionUploadPrepStatement.setLong(3,cart.getUser_id());
                transactionUploadPrepStatement.setString(4,prod.getProd_name());
                transactionUploadPrepStatement.setDate(5,Date.valueOf(LocalDate.now()));
                transactionUploadPrepStatement.setDouble(6,(prod.getPrice() * m.getValue()));
                transactionUploadPrepStatement.executeUpdate();
            }
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
