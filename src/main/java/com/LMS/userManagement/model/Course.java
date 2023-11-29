package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;
    private String title;
    private String authorName;
    private String description;
    @Column(columnDefinition = "TEXT")
    private String thumbNail;
    private Long enrolled;
    private String category;
    private Integer ratings;


}
