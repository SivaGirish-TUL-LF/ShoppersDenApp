package com.shoppersDenApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private long transactionId;
    private long productId;
    private String productName;
    private int quantity;
    private double cost;
    private long customerId;
    private LocalDate dop;

}
