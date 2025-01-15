package com.example.courseManage;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class CourseDB {

    private static final String URL = "jdbc:mysql://localhost:3306/springdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public ArrayList<Course> dbShow() throws Exception {
        ArrayList<Course> courses = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM course");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Course p = new Course();
                p.setCourseId(rs.getInt("course_id"));
                p.setCourseName(rs.getString("course_name"));
                p.setDescription(rs.getString("description"));
                p.setFeeAmount(rs.getInt("fee_amount"));
                courses.add(p);
            }
        }
        return courses;
    }

    public Course findById(int id) throws Exception {
        Course course = null;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM course WHERE course_id = ?")) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    course = new Course(rs.getString("course_name"),
                            rs.getString("description"),
                            rs.getInt("fee_amount"));
                    course.setCourseId(rs.getInt("course_id"));
                }
            }
        }
        return course;
    }


}
