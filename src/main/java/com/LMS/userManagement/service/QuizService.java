package com.LMS.userManagement.service;
import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.repository.QuizRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class QuizService {
    @Autowired
    QuizRankRepository quizRankRepository;

    public QuizRank saveBadge(QuizRank quizRank) {
        Optional<QuizRank> obj=   quizRankRepository.findByUserIdAndSectionId(quizRank.getUserId(),quizRank.getSectionId());
        if(obj.isPresent()) {
            QuizRank quizRank1 = obj.get();
            quizRank1.setEnergyPoints(quizRank.getEnergyPoints());
            quizRank1.setBadge(quizRank.getBadge());
            return quizRankRepository.save(quizRank1);
        }
        return quizRankRepository.save(quizRank);

    }
}
