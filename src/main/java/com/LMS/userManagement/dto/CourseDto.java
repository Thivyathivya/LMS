package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    public Long courseId;
    public String title;
    public String authorName;
    public String description;
    public String thumbNail;
    public Long enrolled;
    public String category;
    public Integer ratings;
}
