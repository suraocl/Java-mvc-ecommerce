package com.onur.finalodev.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.onur.finalodev.model.Category;

@Repository
public class CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getAllCategories() {
        String sqlGetCategories = "SELECT * FROM category";
        return jdbcTemplate.query(sqlGetCategories, (rs, rowNum) -> {
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            return category;
        });
    }

    public Category getCategoryById(int categoryId) {
        String sqlGetCategoryById = "SELECT * FROM category WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlGetCategoryById, new Object[]{categoryId}, (rs, rowNum) -> {
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            return category;
        });
    }

    public void addCategory(Category category) {
        String sqlInsertCategory = "INSERT INTO category (name) VALUES (?)";
        jdbcTemplate.update(sqlInsertCategory, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, category.getName());
            }
        });
    }

    public void updateCategory(Category category) {
        String sqlUpdateCategory = "UPDATE category SET name = ? WHERE id = ?";
        jdbcTemplate.update(sqlUpdateCategory, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, category.getName());
                ps.setInt(2, category.getId());
            }
        });
    }

    public void deleteCategory(int categoryId) {
        String sqlDeleteCategory = "DELETE FROM category WHERE id = ?";
        jdbcTemplate.update(sqlDeleteCategory, categoryId);
    }
}
