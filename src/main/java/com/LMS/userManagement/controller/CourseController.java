package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/api/user")
public class CourseController {
    @Autowired
    CourseService courseService;
    @PostMapping("/saveCourse")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<?> saveCourse(@RequestBody List<Course> course){
     List<Course> courseDto = courseService.saveCourse(course);
    if(courseDto != null){
         return ResponseEntity.ok(courseDto);
    }else {
        return ResponseEntity.ok("Course already exists");
    }
    }
    @GetMapping("/getCourseById")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<?> searchCourseById(@RequestHeader Long courseId){
        Course course = courseService.searchCourseById(courseId);
        if(course != null){
            return ResponseEntity.ok(course);
        }else {
            return ResponseEntity.ok("Course not found");
        }
    }
    @GetMapping("/getAllCourse")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<?> searchAllCourse(){
       List<Course> course = courseService.searchAllCourse();
        if(course != null){
            return ResponseEntity.ok(course);
        }else {
            return ResponseEntity.ok("Course not found");
        }
    }



}
