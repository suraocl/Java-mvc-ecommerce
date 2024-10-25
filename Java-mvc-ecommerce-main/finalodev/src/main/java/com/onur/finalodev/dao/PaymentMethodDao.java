package com.onur.finalodev.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.onur.finalodev.model.PaymentMethod;

@Repository
public class PaymentMethodDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PaymentMethod> getAllPaymentMethods() {
        String sqlGetPaymentMethods = "SELECT * FROM paymentmethod";
        return jdbcTemplate.query(sqlGetPaymentMethods, (rs, rowNum) -> {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setId(rs.getInt("id"));
            paymentMethod.setName(rs.getString("methodName"));
            return paymentMethod;
        });
    }

    public PaymentMethod getPaymentMethodById(int paymentMethodId) {
        String sqlGetPaymentMethodById = "SELECT * FROM paymentmethod WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlGetPaymentMethodById, new Object[]{paymentMethodId}, (rs, rowNum) -> {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setId(rs.getInt("id"));
            paymentMethod.setName(rs.getString("methodName"));
            return paymentMethod;
        });
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        String sqlInsertPaymentMethod = "INSERT INTO paymentmethod (methodName) VALUES (?)";
        jdbcTemplate.update(sqlInsertPaymentMethod, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, paymentMethod.getName());
            }
        });
    }

    public void updatePaymentMethod(PaymentMethod paymentMethod) {
        String sqlUpdatePaymentMethod = "UPDATE paymentmethod SET methodName = ? WHERE id = ?";
        jdbcTemplate.update(sqlUpdatePaymentMethod, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, paymentMethod.getName());
                ps.setInt(2, paymentMethod.getId());
            }
        });
    }

    public void deletePaymentMethod(int paymentMethodId) {
        String sqlDeletePaymentMethod = "DELETE FROM paymentmethod WHERE id = ?";
        jdbcTemplate.update(sqlDeletePaymentMethod, paymentMethodId);
    }
}
