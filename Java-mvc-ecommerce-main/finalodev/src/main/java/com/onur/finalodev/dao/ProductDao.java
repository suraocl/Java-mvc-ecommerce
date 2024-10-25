package com.onur.finalodev.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.onur.finalodev.model.Product;

@Repository
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> getAllProducts() {
        String sqlGetProducts = "SELECT * FROM product";
        return jdbcTemplate.query(sqlGetProducts, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setImageUrl(rs.getString("imageUrl"));
            product.setCategoryId(rs.getInt("categoryId"));
            product.setDescription(rs.getString("description"));
            return product;
        });
    }

    public Product getProductById(int productId) {
        String sqlGetProductById = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlGetProductById, new Object[]{productId}, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setImageUrl(rs.getString("imageUrl"));
            product.setCategoryId(rs.getInt("categoryId"));
            product.setDescription(rs.getString("description"));
            return product;
        });
    }

    public void addProduct(Product product) {
        String sqlInsertProduct = "INSERT INTO product (name, price, imageUrl, categoryId, description) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsertProduct, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, product.getName());
                ps.setDouble(2, product.getPrice());
                ps.setString(3, product.getImageUrl());
                ps.setInt(4, product.getCategoryId());
                ps.setString(5, product.getDescription());
            }
        });
    }

    public void updateProduct(Product product) {
        String sqlUpdateProduct = "UPDATE product SET name = ?, price = ?, imageUrl = ?, categoryId = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sqlUpdateProduct, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, product.getName());
                ps.setDouble(2, product.getPrice());
                ps.setString(3, product.getImageUrl());
                ps.setInt(4, product.getCategoryId());
                ps.setString(5, product.getDescription());
                ps.setInt(6, product.getId());
            }
        });
    }

    public void deleteProduct(int productId) {
        String sqlDeleteProduct = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sqlDeleteProduct, productId);
    }

    public List<Product> getProductsByCategoryName(String categoryName) {
        String sql = "SELECT p.* FROM product p " +
                     "INNER JOIN category c ON p.categoryId = c.id " +
                     "WHERE c.name = ?";
        
        return jdbcTemplate.query(sql, new Object[]{categoryName}, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setImageUrl(rs.getString("imageUrl"));
            product.setCategoryId(rs.getInt("categoryId"));
            product.setDescription(rs.getString("description"));
            return product;
        });
    }

    public List<Product> getProductsByName(String name) {
        String sqlGetProductsByName = "SELECT * FROM product WHERE name LIKE ?";
        return jdbcTemplate.query(sqlGetProductsByName, new Object[]{"%" + name + "%"}, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setImageUrl(rs.getString("imageUrl"));
            product.setCategoryId(rs.getInt("categoryId"));
            product.setDescription(rs.getString("description"));
            return product;
        });
    }
}
