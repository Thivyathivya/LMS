package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubSectionDto {
    private UUID subSectionId;
    private Integer sectionId;
    private Integer key;
    private String title;
    private String description;
    private String link;
    private List<QuizDto> quizList;

}
