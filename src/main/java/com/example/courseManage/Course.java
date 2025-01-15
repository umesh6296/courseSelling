package com.example.courseManage;

import jakarta.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private int courseId;

    @Column(name="course_name")
    private String courseName;

    @Column(name="description")
    private String description;

    @Column(name="fee_amount")
    private int feeAmount;

    public Course(String courseName, String description, Integer feeAmount) {
        this.courseName = courseName;
        this.description = description;
        this.feeAmount = feeAmount;
    }

    public Course() {}

    // Getters and setters

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(int feeAmount) {
        this.feeAmount = feeAmount;
    }
}
