package com.example.courseManage;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class EnrollmentDB {

    private static final String URL = "jdbc:mysql://localhost:3306/springdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public void insert(Enrollment enrollment) throws Exception {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "INSERT INTO enrollment (stud_name, phone, email, pass, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, enrollment.getStudentName());
            pst.setLong(2, enrollment.getPhone());
            pst.setString(3, enrollment.getEmail());
            pst.setString(4, enrollment.getPassword());
            pst.setString(5, enrollment.getAddress());

            pst.executeUpdate();

    }

    public Enrollment find(String email, String password) throws Exception {
        Enrollment enrollment = null;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM enrollment WHERE email = ? AND pass = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    enrollment = new Enrollment();
                    enrollment.setStudentName(rs.getString("stud_name"));
                    enrollment.setPhone(rs.getLong("phone"));
                    enrollment.setEmail(rs.getString("email"));
                    enrollment.setPassword(rs.getString("pass"));
                    enrollment.setAddress(rs.getString("address"));
                }
            }
        }
        return enrollment;
    }
}
