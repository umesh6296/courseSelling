package com.example.courseManage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Fee {

    @Id
    @Column(name="course_name")
    private String courseName;
    @Column(name="id")
    private int id;

    @Column(name="email")
    private String email;


    @Column(name="fee_amount")
    private int feeAmount;

    @Column(name="date_of_fee")
    private Date date;

    public Fee(String courseName, int feeAmount, Date date,int id,String email) {
        this.courseName = courseName;
        this.feeAmount = feeAmount;
        this.date = date;
        this.id=id;
        this.email=email;
    }
    public Fee(){

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(int feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
