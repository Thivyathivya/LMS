package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/api/user")
public class UserController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/userRead")
    @PreAuthorize("hasAuthority('user')")
    public String userRead(){

        return "User can  read";
    }

    @GetMapping("/getAllCourse")
    @PreAuthorize("hasAuthority('user')")
    public List<Course> getAllCourse(){
      return   courseRepository.findAll();

    }
}
