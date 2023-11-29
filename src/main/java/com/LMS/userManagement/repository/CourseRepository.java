package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findCourseByCourseId(Long courseId);
}
