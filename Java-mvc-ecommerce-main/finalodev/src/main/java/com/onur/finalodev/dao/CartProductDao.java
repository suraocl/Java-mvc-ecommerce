package com.onur.finalodev.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.onur.finalodev.model.CartProduct;

@Repository
public class CartProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CartProduct> getAllCartProducts() {
        String sqlGetCartProducts = "SELECT * FROM cart_product";
        return jdbcTemplate.query(sqlGetCartProducts, (rs, rowNum) -> {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setCartId(rs.getInt("cartId"));
            cartProduct.setProductId(rs.getInt("productId"));
            cartProduct.setQuantity(rs.getInt("quantity"));
            return cartProduct;
        });
    }

    public CartProduct getCartProductById(int cartId, int productId) {
        String sqlGetCartProductById = "SELECT * FROM cart_product WHERE cartId = ? AND productId = ?";
        return jdbcTemplate.queryForObject(sqlGetCartProductById, new Object[]{cartId, productId}, (rs, rowNum) -> {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setCartId(rs.getInt("cartId"));
            cartProduct.setProductId(rs.getInt("productId"));
            cartProduct.setQuantity(rs.getInt("quantity"));
            return cartProduct;
        });
    }
    public List<CartProduct> getCartProductsByCartId(int cartId) {
        String sqlGetCartProductByCartId = "SELECT * FROM cart_product WHERE cartId = ?";
        return jdbcTemplate.query(sqlGetCartProductByCartId, new Object[]{cartId}, (rs, rowNum) -> {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setCartId(rs.getInt("cartId"));
            cartProduct.setProductId(rs.getInt("productId"));
            cartProduct.setQuantity(rs.getInt("quantity"));
            return cartProduct;
        });
    }


    public void addCartProduct(CartProduct cartProduct) {
        String sqlInsertCartProduct = "INSERT INTO cart_product (cartId, productId, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlInsertCartProduct, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, cartProduct.getCartId());
                ps.setInt(2, cartProduct.getProductId());
                ps.setInt(3, cartProduct.getQuantity());
            }
        });
    }

    public void updateCartProduct(CartProduct cartProduct) {
        String sqlUpdateCartProduct = "UPDATE cart_product SET quantity = ? WHERE cartId = ? AND productId = ?";
        jdbcTemplate.update(sqlUpdateCartProduct, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, cartProduct.getQuantity());
                ps.setInt(2, cartProduct.getCartId());
                ps.setInt(3, cartProduct.getProductId());
            }
        });
    }

    public void deleteCartProduct(int cartId, int productId) {
        String sqlDeleteCartProduct = "DELETE FROM cart_product WHERE cartId = ? AND productId = ?";
        jdbcTemplate.update(sqlDeleteCartProduct, cartId, productId);
    }
}
