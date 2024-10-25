package com.onur.finalodev.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.onur.finalodev.model.Order;

@Repository
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Order> getAllOrders() {
        String sqlGetOrders = "SELECT * FROM `order`";
        return jdbcTemplate.query(sqlGetOrders, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("userId"));
            order.setTotalPrice(rs.getDouble("totalPrice"));
            order.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            order.setAddress(rs.getString("address"));
            order.setStatus(rs.getString("status"));
            order.setPaymentMethodId(rs.getInt("paymentMethodId"));
            return order;
        });
    }

    public Order getOrderById(int orderId) {
        String sqlGetOrderById = "SELECT * FROM `order` WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlGetOrderById, new Object[]{orderId}, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("userId"));
            order.setTotalPrice(rs.getDouble("totalPrice"));
            order.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            order.setAddress(rs.getString("address"));
            order.setStatus(rs.getString("status"));
            order.setPaymentMethodId(rs.getInt("paymentMethodId"));
            return order;
        });
    }

    public int addOrder(Order order) {
        String sqlInsertOrder = "INSERT INTO `order` (userId, totalPrice, createdAt, address, paymentMethodId) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sqlInsertOrder, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setTimestamp(3, Timestamp.valueOf(order.getCreatedAt()));
            ps.setString(4, order.getAddress());
            ps.setInt(5, order.getPaymentMethodId());
            return ps;
        }, keyHolder);
        
        return keyHolder.getKey().intValue();
    }

    public List<Order> getOrdersByUserEmail(String email) {
        String sqlGetOrdersByUserEmail = "SELECT o.* FROM `order` o INNER JOIN `user` u ON o.userId = u.id WHERE u.email LIKE ?";
        return jdbcTemplate.query(sqlGetOrdersByUserEmail, new Object[]{"%" + email + "%"}, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("userId"));
            order.setTotalPrice(rs.getDouble("totalPrice"));
            order.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            order.setAddress(rs.getString("address"));
            order.setStatus(rs.getString("status"));
            order.setPaymentMethodId(rs.getInt("paymentMethodId"));
            return order;
        });
    }
    public void updateOrder(Order order) {
        String sqlUpdateOrder = "UPDATE `order` SET userId = ?, totalPrice = ?, createdAt = ?, address = ?, paymentMethodId = ? , status = ? WHERE id = ?";
        jdbcTemplate.update(sqlUpdateOrder, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, order.getUserId());
                ps.setDouble(2, order.getTotalPrice());
                ps.setTimestamp(3, Timestamp.valueOf(order.getCreatedAt()));
                ps.setString(4, order.getAddress());
                ps.setInt(5, order.getPaymentMethodId());
                ps.setString(6, order.getStatus());
                ps.setInt(7, order.getId());
            }
        });
    }
    public List<Order> getOrdersByUserId(int userId) {
        String sqlGetOrdersByUserId = "SELECT * FROM `order` WHERE userId = ?";
        return jdbcTemplate.query(sqlGetOrdersByUserId, new Object[]{userId}, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("userId"));
            order.setTotalPrice(rs.getDouble("totalPrice"));
            order.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            order.setAddress(rs.getString("address"));
            order.setStatus(rs.getString("status"));
            order.setPaymentMethodId(rs.getInt("paymentMethodId"));
            return order;
        });
    }
    public void deleteOrder(int orderId) {
        String sqlDeleteOrder = "DELETE FROM `order` WHERE id = ?";
        jdbcTemplate.update(sqlDeleteOrder, orderId);
    }
}
