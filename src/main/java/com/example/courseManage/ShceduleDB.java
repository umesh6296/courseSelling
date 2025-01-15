package com.example.courseManage;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShceduleDB {

    private static final String URL = "jdbc:mysql://localhost:3306/springdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";


    public List<Schedule> findByCourseId(int courseId) throws Exception {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM schedule WHERE course_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, courseId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setScheduleId(rs.getInt("schedule_id"));
                    schedule.setStartDate(rs.getString("startDate"));
                    schedule.setEndDate(rs.getString("endDate"));
                    schedule.setTime(rs.getString("time"));
                    schedule.setDuration(rs.getString("duration"));
                    schedule.setClassType(rs.getString("classType"));
                    schedule.setCourseId(rs.getInt("course_id"));
                    schedules.add(schedule);
                }
            }
        }
        return schedules;
    }


}
