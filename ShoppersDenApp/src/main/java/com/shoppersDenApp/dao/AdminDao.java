package com.shoppersDenApp.dao;

import java.sql.SQLException;
import java.time.LocalDate;

public interface AdminDao {
    boolean addProdToCategory(long prod_id, String prod_name, String prod_desc, String img_url,LocalDate dom,double price,long cate_id) throws SQLException;
    boolean updateAccount();
    boolean updateProdToCategory();
}
