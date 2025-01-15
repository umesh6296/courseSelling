package com.example.courseManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseRestController {

    private final CourseDB courseDB;
    private final ShceduleDB shceduleDB;
    private final EnrollmentDB enrollmentDB;
    private final RegisterCourseDB registerCourseDB;
    private final FeeDB feeDB;
    private ScheduleService scheduleService;

    @Autowired
    public CourseRestController(CourseDB courseDB,ShceduleDB shceduleDB,EnrollmentDB enrollmentDB,RegisterCourseDB registerCourseDB,FeeDB feeDB,ScheduleService scheduleService) {
        this.courseDB = courseDB;
        this.shceduleDB=shceduleDB;
        this.enrollmentDB=enrollmentDB;
        this.registerCourseDB=registerCourseDB;
        this.feeDB=feeDB;
        this.scheduleService=scheduleService;
    }

    @GetMapping("/courses")
    public ArrayList<Course> show() throws Exception {
        return courseDB.dbShow();
    }

    @GetMapping("/courses/{id}")
    public Course find(@PathVariable int id) throws Exception {
        return courseDB.findById(id);
    }


    @GetMapping("/schedules/{courseId}")
    public List<Schedule> findSchedulesByCourseId(@PathVariable int courseId) throws Exception {
        return shceduleDB.findByCourseId(courseId);
    }
    @GetMapping("/login")
    public String findLogin(@PathVariable String email, @PathVariable String password) throws Exception {
        Enrollment enrollment = enrollmentDB.find(email, password);
        if (enrollment != null) {
            return "Login successful!";
        } else {
            return "Invalid email and password.";
        }
    }

    @PostMapping("/save")
    public void insert(@RequestBody Enrollment enrollment)throws Exception{
        enrollmentDB.insert(enrollment);
    }
    @PostMapping("/insert")
    public void insertRegister(@RequestBody RegisterCourse RegisterCourse)throws Exception{
        registerCourseDB.insert(RegisterCourse);
    }
    @GetMapping("/registered-courses/{email}")
    public List<RegisterCourse> findRegisteredCoursesByEmail(@PathVariable String email)throws Exception {
           return registerCourseDB.findByEmail(email);
    }

    @PostMapping("/insertFee")
    public void insertFee(@RequestBody Fee fee)throws Exception{
        feeDB.insert(fee);
    }

    @GetMapping("/fees/{email}")
    public List<Fee> findFeesByCourseName(@PathVariable String email,@PathVariable String courseName) throws Exception {
        return feeDB.showByCourseName(email,courseName);
    }
}
