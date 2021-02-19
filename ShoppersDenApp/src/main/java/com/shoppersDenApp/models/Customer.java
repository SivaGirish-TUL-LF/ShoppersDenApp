package com.shoppersDenApp.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Address address;
    private String email;
    private String name;
    private long phoneNo;
    private String pwd;
    private String secretQ;
    private String secretA;
    private long customer_id;
}
