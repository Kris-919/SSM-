package com.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void updateBalance(String userName,int price){
        String sql="update account set balance=balance-? where userName=?";
        jdbcTemplate.update(sql,price,userName);
    }

    public void updateStock(String isbn) {
        String sql="update book_stock set stock=stock-1 where isbn=?";
        jdbcTemplate.update(sql,isbn);
    }

    public int getPrice(String isbn) {
        String sql="select price from book where isbn=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,isbn);
    }
}
