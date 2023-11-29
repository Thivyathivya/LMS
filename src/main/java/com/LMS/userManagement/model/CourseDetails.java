package com.LMS.userManagement.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetails {
    private Long courseId;
    private String title;
    private String authorName;
    private String description;
    @Column(columnDefinition = "TEXT")
    private String thumbNail;
    private Long enrolled;
    private String category;
    private Integer ratings;
    private String Language;
    private Long lastUpdate;
    private String overview;
   /* @MapsId
    @OneToOne
    @JoinColumn(name = "course_id")
    public Course course;*/
}
