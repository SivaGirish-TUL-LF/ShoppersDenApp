package com.shoppersDenApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private LocalDate dom;
    private String img_url;
    private String prod_desc;
    private long prod_id;
    private String prod_name;
    private double price;
    private long cate_id;


}
