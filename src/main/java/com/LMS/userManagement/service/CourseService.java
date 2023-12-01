package com.LMS.userManagement.service;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;


    public Course searchCourseById(Long courseId) {
        return courseRepository.findCourseByCourseId(courseId);
    }


    public List<Course> saveCourse(List<Course> course) {
        return courseRepository.saveAll(course);
    }

    public List<Course> searchAllCourse() {
        return courseRepository.findAll();
    }
}
