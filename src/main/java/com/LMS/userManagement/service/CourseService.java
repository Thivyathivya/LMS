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
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private SectionRepository sectionRepository;


    public Course searchCourseById(Integer courseId) {
        return courseRepository.findCourseByCourseId(courseId);
    }


    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }


    public List<Course> searchCourses(String search) {
        return courseRepository.searchAllCourse(search);
    }

    public Page<Course> getAllCourses(int pageNo, int pageSize) {
        return courseRepository.findAll(PageRequest.of(pageNo,pageSize));
    }

    public ResponseEntity<?> saveSection(List<Section> sections) {
        if (sections != null) {
            return ResponseEntity.ok(sectionRepository.saveAll(sections));
        }
        return ResponseEntity.ok("Failure");
    }

    /*public ResponseEntity<?> searchCourse(Integer courseId) {
        Optional<Course> course =courseRepository.findById(courseId);
        if (course != null){
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.ok("course not found");
    }*/
}
