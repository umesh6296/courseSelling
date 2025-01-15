package com.example.courseManage;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "schedule") // Ensure the correct table name is specified
public class Schedule {
    public Schedule(String startDate, String endDate, String time, String duration, String classType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        this.duration = duration;
        this.classType = classType;
    }
    public Schedule(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id") // Mapping to schedule_id in the database
    private int scheduleId;

    @Column(name = "startDate") // Mapping to startDate in the database
    private String startDate;

    @Column(name = "endDate") // Mapping to endDate in the database
    private String endDate;

    @Column(name = "time") // Mapping to time in the database
    private String time;

    @Column(name = "duration") // Mapping to duration in the database
    private String duration;

    @Column(name = "classType") // Mapping to classType in the database
    private String classType;

    @Column(name = "course_id") // Mapping to course_id in the database
    private int courseId;



    // Getters and setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", time=" + time +
                ", duration=" + duration +
                ", classType='" + classType + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
