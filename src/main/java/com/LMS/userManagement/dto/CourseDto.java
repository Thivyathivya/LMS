package com.LMS.userManagement.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    public UUID courseId;
    public String title;
    public String authorName;
    public String description;
    public String thumbNail;
    public Long enrolled;
    public String category;
    public Integer ratings;
    private String language;
    private String overview;
    private String whatYouWillLearn;
    private Integer price;
    private Date date;
    private List<SectionDto> sections;
}
