package com.onur.finalodev.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.onur.finalodev.model.Cart;

@Repository
public class CartDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cart> getAllCarts() {
        String sqlGetCarts = "SELECT * FROM cart";
        return jdbcTemplate.query(sqlGetCarts, (rs, rowNum) -> {
            Cart cart = new Cart();
            cart.setId(rs.getInt("id"));
            return cart;
        });
    }

    public Cart getCartById(int cartId) {
        String sqlGetCartById = "SELECT * FROM cart WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlGetCartById, new Object[]{cartId}, (rs, rowNum) -> {
            Cart cart = new Cart();
            cart.setId(rs.getInt("id"));
            return cart;
        });
    }

    public void addCart(Cart cart) {
        String sqlInsertCart = "INSERT INTO cart () VALUES ()";
        jdbcTemplate.update(sqlInsertCart, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
            }
        });
    }
    public int createCart() {
        String sqlInsertCart = "INSERT INTO cart VALUES (NULL)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsertCart, Statement.RETURN_GENERATED_KEYS);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
    public void deleteCart(int cartId) {
        String sqlDeleteCart = "DELETE FROM cart WHERE id = ?";
        jdbcTemplate.update(sqlDeleteCart, cartId);
    }
}
