package com.example.courseManage;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterCourseDB {
    private static final String URL = "jdbc:mysql://localhost:3306/springdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    public void insert(RegisterCourse registerCourse) throws Exception {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "INSERT INTO registered_courses (email, course_name, schedule_id) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, registerCourse.getEmail());
            pst.setString(2, registerCourse.getCourseName());
            pst.setInt(3, 2);
            pst.executeUpdate();

    }

    public List<RegisterCourse> findByEmail(String email) throws Exception {
        List<RegisterCourse> courses = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "SELECT email, course_name, schedule_id FROM registered_courses WHERE email = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String courseName = rs.getString("course_name");
                int scheduleId = rs.getInt("schedule_id");

                RegisterCourse course = new RegisterCourse();
                course.setEmail(email);
                course.setCourseName(courseName);
                course.setScheduleId(scheduleId);

                courses.add(course);
            }
            return courses;
    }


}
