package com.onur.finalodev.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.onur.finalodev.model.OrderProduct;

@Repository
public class OrderProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<OrderProduct> getAllOrderProducts() {
        String sqlGetOrderProducts = "SELECT * FROM order_product";
        return jdbcTemplate.query(sqlGetOrderProducts, (rs, rowNum) -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(rs.getInt("orderId"));
            orderProduct.setProductId(rs.getInt("productId"));
            orderProduct.setQuantity(rs.getInt("quantity"));
            return orderProduct;
        });
    }

    public OrderProduct getOrderProductById(int orderId, int productId) {
        String sqlGetOrderProductById = "SELECT * FROM order_product WHERE orderId = ? AND productId = ?";
        return jdbcTemplate.queryForObject(sqlGetOrderProductById, new Object[]{orderId, productId}, (rs, rowNum) -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(rs.getInt("orderId"));
            orderProduct.setProductId(rs.getInt("productId"));
            orderProduct.setQuantity(rs.getInt("quantity"));
            return orderProduct;
        });
    }

    public List<OrderProduct> getOrderProductsByOrderId(int orderId) {
        String sqlGetOrderProductsByUserId = 
            "SELECT op.* FROM order_product op " +
            "JOIN `order` o ON op.orderId = o.id " +
            "WHERE op.orderId = ?";
        
        return jdbcTemplate.query(sqlGetOrderProductsByUserId, new Object[]{orderId}, (rs, rowNum) -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(rs.getInt("orderId"));
            orderProduct.setProductId(rs.getInt("productId"));
            orderProduct.setQuantity(rs.getInt("quantity"));
            return orderProduct;
        });
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        String sqlInsertOrderProduct = "INSERT INTO order_product (orderId, productId, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlInsertOrderProduct, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, orderProduct.getOrderId());
                ps.setInt(2, orderProduct.getProductId());
                ps.setInt(3, orderProduct.getQuantity());
            }
        });
    }

    public void updateOrderProduct(OrderProduct orderProduct) {
        String sqlUpdateOrderProduct = "UPDATE order_product SET quantity = ? WHERE orderId = ? AND productId = ?";
        jdbcTemplate.update(sqlUpdateOrderProduct, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, orderProduct.getQuantity());
                ps.setInt(2, orderProduct.getOrderId());
                ps.setInt(3, orderProduct.getProductId());
            }
        });
    }

    public void deleteOrderProduct(int orderId, int productId) {
        String sqlDeleteOrderProduct = "DELETE FROM order_product WHERE orderId = ? AND productId = ?";
        jdbcTemplate.update(sqlDeleteOrderProduct, orderId, productId);
    }
    
    public List<OrderProduct> getOrderProductsByUserId(int userId) {
        String sqlGetOrderProductsByUserId = 
            "SELECT op.* FROM order_product op " +
            "JOIN `order` o ON op.orderId = o.id " +
            "WHERE o.userId = ?";
        
        return jdbcTemplate.query(sqlGetOrderProductsByUserId, new Object[]{userId}, (rs, rowNum) -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(rs.getInt("orderId"));
            orderProduct.setProductId(rs.getInt("productId"));
            orderProduct.setQuantity(rs.getInt("quantity"));
            return orderProduct;
        });
    }
}
