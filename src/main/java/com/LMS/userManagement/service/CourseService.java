package com.LMS.userManagement.service;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.Section;
import com.LMS.userManagement.repository.CourseRepository;
import com.LMS.userManagement.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private SectionRepository sectionRepository;


    public Course searchCourseById(Integer courseId) {
        return courseRepository.findCourseByCourseId(courseId);
    }


    public List<Course> saveCourse(List<Course> course) {
        return courseRepository.saveAll(course);
    }


    public List<Course> searchCourses(String search) {
        return courseRepository.searchAllCourse(search);
    }

    public Page<Course> getAllCourses(int pageNo, int pageSize) {
        return courseRepository.findAll(PageRequest.of(pageNo,pageSize));
    }

    public ResponseEntity<?> saveSection(Integer courseId,List<Section> sections) {
            Course course = courseRepository.findCourseByCourseId(courseId);
        if (course != null){
            List<Section> savedSections = sectionRepository.saveAll(sections);
            return ResponseEntity.ok(savedSections);
        }
        return ResponseEntity.ok("Course not found");
    }

}
