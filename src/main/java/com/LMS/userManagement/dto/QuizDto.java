package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private UUID quizId;
    private UUID subSectionId;
    private String title;
    private Integer key;
    private String question;
    private List<String> options;
    private  int answer;
}
