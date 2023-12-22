package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*")

public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("/saveBadge")
    public ResponseEntity<?> saveBadge(@RequestBody QuizRank quizRank){
        QuizRank quizRank1 = quizService.saveBadge(quizRank);
    if (quizRank1 != null){
        return ResponseEntity.ok(quizRank1);
    }
    return ResponseEntity.ok("Gamification not found");
    }
}
