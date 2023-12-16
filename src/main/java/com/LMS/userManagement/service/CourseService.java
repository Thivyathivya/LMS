package com.LMS.userManagement.service;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;


    public Course searchCourseById(Integer courseId) {
        return courseRepository.findCourseByCourseId(courseId);
    }


    public List<Course> saveCourse(List<Course> course) {
        return courseRepository.saveAll(course);
    }


    public List<Course> searchCourses(String title, String description, String category) {
        return courseRepository.findCoursesByTitleDescriptionCategory(title, description, category);
    }

    public Page<Course> getAllCourses(int pageNo, int pageSize) {
        return courseRepository.findAll(PageRequest.of(pageNo,pageSize));
    }
}
