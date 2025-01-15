package com.example.courseManage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RegisterCourse {

    @Column(name="course_name")
    private String courseName;
    @Column(name="Id")
    private int id;

    @Column(name = "email")
    private String email;

    @Id

    @Column(name = "schedule_id")
    private int scheduleId;

    public RegisterCourse(String courseName, String email, int scheduleId,int id) {
        this.courseName = courseName;
        this.email = email;
        this.scheduleId = scheduleId;
        this.id=id;
    }
    public RegisterCourse(){

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
