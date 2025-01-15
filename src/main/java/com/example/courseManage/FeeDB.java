package com.example.courseManage;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class FeeDB {
    private static final String URL = "jdbc:mysql://localhost:3306/springdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    public void insert(Fee fee) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "INSERT INTO fee (course_name, fee_amount, email) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, fee.getCourseName());
            pst.setInt(2, fee.getFeeAmount());
            pst.setString(3, fee.getEmail());
            pst.executeUpdate();
        }
    }

    public List<Fee> showByCourseName(String email, String courseName) throws Exception {
        List<Fee> feeList = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT * FROM fee WHERE email = ? AND course_name = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, email);
            pst.setString(2, courseName);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Fee fee = new Fee();
                    fee.setCourseName(rs.getString("course_name"));
                    fee.setFeeAmount(rs.getInt("fee_amount"));
                    fee.setDate(rs.getDate("date_of_fee"));
                    feeList.add(fee);
                }
            }
        }
        return feeList;
    }

}
