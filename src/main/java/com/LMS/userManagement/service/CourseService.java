package com.LMS.userManagement.service;
import com.LMS.userManagement.dto.*;
import com.LMS.userManagement.model.Course;
import com.LMS.userManagement.model.Quiz;
import com.LMS.userManagement.model.Section;
import com.LMS.userManagement.model.SubSection;
import com.LMS.userManagement.repository.CourseRepository;
import com.LMS.userManagement.repository.SectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    SectionService sectionService;


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

    public ResponseEntity<?> deleteCourseById(Integer courseId) {
        if (courseRepository.existsById(courseId)){
            courseRepository.deleteById(courseId);
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.ok("Course not found");
    }
    @Transactional
    public ResponseEntity<?> updateCourse(CourseDto courseDto) {
        Optional<Course> course1=courseRepository.findById(courseDto.getCourseId());
        Course course2 = course1.get();
        course2.setTitle(courseDto.getTitle());
        course2.setAuthorName(courseDto.getAuthorName());
        course2.setCategory(courseDto.getCategory());
        course2.setDescription(courseDto.getDescription());
        course2.setLanguage(courseDto.getLanguage());
        course2.setOverview(courseDto.getOverview());
        course2.setEnrolled(courseDto.getEnrolled());
        course2.setDate(courseDto.getDate());
        course2.setPrice(courseDto.getPrice());
        course2.setRatings(courseDto.getRatings());
        course2.setThumbNail(courseDto.getThumbNail());
        course2.setWhatYouWillLearn(courseDto.getWhatYouWillLearn());
       return ResponseEntity.ok(courseRepository.save(course2));
    }
    @Transactional
    public ResponseEntity<?> updateSections(UpdateRequest updateRequest) {
        List<Section> updatedSections = new ArrayList<>();
        for (SectionDto sectionDto : updateRequest.getSections()) {
            Section updatedSection = sectionService.updateSection(sectionDto);
            List<SubSection> updatedSubSections = new ArrayList<>();
            for (SubSectionDto subSectionDto : sectionDto.getSubSections()) {
                SubSection updatedSubSection = sectionService.updateSubSection(subSectionDto);
                List<Quiz> updatedQuizzes = new ArrayList<>();
                for (QuizDto quizDto : subSectionDto.getQuizList()) {
                   Quiz updatedQuiz = sectionService.updateQuiz(quizDto);
                    updatedQuizzes.add(updatedQuiz);
                }
                updatedSubSection.setQuizList(updatedQuizzes);

                updatedSubSections.add(updatedSubSection);
            }
            updatedSection.setSubSections(updatedSubSections);

            updatedSections.add(updatedSection);
        }
        return ResponseEntity.ok(updatedSections);

    }


}
