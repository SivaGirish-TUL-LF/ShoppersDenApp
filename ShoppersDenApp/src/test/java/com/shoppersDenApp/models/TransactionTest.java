package com.shoppersDenApp.models;

import com.shoppersDenApp.dao.TransactionDao;
import com.shoppersDenApp.dao.TransactionDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionTest {
    TransactionDao transactionDao;
    List<Transaction> transactionList;
    @BeforeEach
    public void init() throws SQLException {
        transactionDao = new TransactionDaoImpl();
    }

    @Test
    public void transactionTest(){
        transactionList = transactionDao.sendTransactionList();
        assertTrue(transactionList.stream().allMatch(Objects::nonNull));
    }
}
