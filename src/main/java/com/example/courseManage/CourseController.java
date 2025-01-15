package com.example.courseManage;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {

    private final CourseRestController courseRestController;

    @Autowired
    public CourseController(CourseRestController courseRestController) {
        this.courseRestController = courseRestController;
    }

    @GetMapping("/courses")
    public String getCourses(Model model) throws Exception {
        List<Course> list = courseRestController.show();
        model.addAttribute("list", list);
        return "courseList";
    }

    @GetMapping("/courses/{courseId}")
    public String getCourseDetails(@PathVariable int courseId, Model model, HttpSession session) throws Exception {
        Course course = courseRestController.find(courseId);
        session.setAttribute("courseName", course.getCourseName());
        session.setAttribute("feeDetail", course.getFeeAmount());
        model.addAttribute("course", course);
        return "courseDetails";
    }

    @GetMapping("/enroll/{courseId}")
    public String enrollCourse(@PathVariable int courseId, Model model) throws Exception {
        List<Schedule> schedules = courseRestController.findSchedulesByCourseId(courseId);
        model.addAttribute("schedules", schedules);
        return "CourseShcedule"; // courseSchedule.html Thymeleaf template
    }

    @GetMapping("/schedule/{courseId}")
    public String getSchedule(@PathVariable int courseId, Model model) throws Exception {
        List<Schedule> schedules = courseRestController.findSchedulesByCourseId(courseId);
        model.addAttribute("schedules", schedules);
        return "CourseShcedule"; // courseSchedule.html Thymeleaf template
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        String courseName = (String) session.getAttribute("courseName");
        Schedule schedule = new Schedule();

        System.out.println("=========schedule id=======" + schedule.getScheduleId());
        if (courseName != null) {
            session.setAttribute("courseName", courseName);
            System.out.println("==============All Ok===================");
        }
        return "Login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String password, HttpSession session,@ModelAttribute("fee") Fee fee, Model model) throws Exception {
        String message = courseRestController.findLogin(email, password);
        model.addAttribute("email", email);
        session.setAttribute("email", email);

        if (message.equals("Login successful!")) {
            System.out.println("===============Login Successfully================");
            String courseName = (String) session.getAttribute("courseName");
            Integer scheduleId = (Integer) session.getAttribute("scheduleId");
            System.out.println("====course Name========" + courseName);
            System.out.println("====Schedule id========" + scheduleId);
            RegisterCourse registerCourse = new RegisterCourse();
            registerCourse.setEmail(email);
            registerCourse.setCourseName(courseName);
            courseRestController.insertRegister(registerCourse);

            String emailSession = (String) session.getAttribute("email");
            int feeAmount=(Integer) session.getAttribute("feeDetail");
            fee.setCourseName(courseName);
            fee.setFeeAmount(feeAmount);
            fee.setEmail(emailSession);
            courseRestController.insertFee(fee);

            session.removeAttribute("scheduleId");
            return "redirect:/register_course/" + email; // Redirect to registered course page with email
        } else {
            model.addAttribute("error", message);
            return "Login"; // Stay on login page if login fails
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("enrollment") Enrollment enrollment) throws Exception {
        courseRestController.insert(enrollment); // Save the enrollment data
        return "redirect:/login"; // Redirect to the login page
    }



    @GetMapping("/register")
    public String register() {
        return "Enrollment";
    }

    @GetMapping("/register_course/{email}")
    public String findByEmail(@PathVariable("email") String email, Model model) throws Exception {
        List<RegisterCourse> registeredCourses = courseRestController.findRegisteredCoursesByEmail(email);
        model.addAttribute("registeredCourses", registeredCourses);
        return "RegisterdCourse";
    }

    @GetMapping("/fees/{email}/{courseName}")
    public String getFeesByCourseName(@PathVariable String email,@PathVariable String courseName, Model model,HttpSession session) throws Exception {
        List<Fee> feeList = courseRestController.findFeesByCourseName(email,courseName);
        model.addAttribute("feeList", feeList);
        return "FeeDetail"; // Assuming you have a FeeDetail.html Thymeleaf template
    }

    @GetMapping("/thanks")
    public String thanks() {
        return "Thanks";
    }


}
