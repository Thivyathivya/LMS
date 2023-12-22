package com.LMS.userManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizRank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rankId;
    private Long userId;
    private Integer sectionId;
    private Integer energyPoints;
    private String badge;



}
