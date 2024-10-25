package com.onur.finalodev.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.onur.finalodev.model.User;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private CartDao cartDao;

    public List<User> getAllUsers() {
        String sqlGetUsers = "SELECT * FROM user";
        
        return jdbcTemplate.query(sqlGetUsers, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setCartId(rs.getInt("cartId"));
            user.setRole(rs.getString("role"));
            return user;
        });
    }
    public List<User> searchUsersByEmail(String email) {
        String sqlSearchUsersByEmail = "SELECT * FROM user WHERE email LIKE ?";
        
        return jdbcTemplate.query(sqlSearchUsersByEmail, new Object[]{"%" + email + "%"}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setCartId(rs.getInt("cartId"));
            user.setRole(rs.getString("role"));
            return user;
        });
    }
    public User getUserById(int userId) {
        String sqlGetUserById = "SELECT * FROM user WHERE id = ?";
        
        return jdbcTemplate.queryForObject(sqlGetUserById, new Object[]{userId}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setCartId(rs.getInt("cartId"));
            user.setRole(rs.getString("role"));
            return user;
        });
    }

    public void registerUser(User user) {
        String sqlInsertUser = "INSERT INTO user (name, email, password, cartId) VALUES (?, ?, ?, ?)";
        
        
        try {

            jdbcTemplate.update(sqlInsertUser, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getPassword());
                    ps.setInt(4, user.getCartId());
                }
            });
        } catch (Exception e) {
            throw new RuntimeException();
            // TODO: handle exception
        }
        
    }

    public User loginUser(String email, String password) {
        String sqlGetUserByEmailAndPassword = "SELECT * FROM user WHERE email = ? AND password = ?";
        
        return jdbcTemplate.queryForObject(sqlGetUserByEmailAndPassword, new Object[]{email, password}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setCartId(rs.getInt("cartId"));
            return user;
        });
    }

    public void deleteUser(int userId) {
        String sqlDeleteUser = "DELETE FROM user WHERE id = ?";
        
        try {
            jdbcTemplate.update(sqlDeleteUser, userId);
        } catch (Exception e) {
        	System.out.println(e);
            throw new RuntimeException();
            // TODO: handle exception
        }
    }

    public void updateUser(User user) {
        String sqlUpdateUser = "UPDATE user SET name = ?, email = ?, password = ?, cartId = ?, role = ? WHERE id = ?";

        try {
            jdbcTemplate.update(sqlUpdateUser, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getPassword());
                    ps.setInt(4, user.getCartId());
                    ps.setString(5, user.getRole());
                    ps.setInt(6, user.getId());
                }
            });
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Error updating user: " + e.getMessage());
        }
    }

}
