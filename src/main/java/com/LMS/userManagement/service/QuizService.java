package com.LMS.userManagement.service;
import com.LMS.userManagement.model.BadgeCounts;
import com.LMS.userManagement.model.QuizRank;
import com.LMS.userManagement.repository.QuizRankRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class QuizService {
    @Autowired
    QuizRankRepository quizRankRepository;

    @Transactional
    public BadgeCounts saveBadge(QuizRank quizRank) {
        Optional<QuizRank> obj = quizRankRepository.findByUserIdAndSubSectionId(quizRank.getUserId(), quizRank.getSubSectionId());
        if (obj.isPresent()) {
            QuizRank quizRank1 = obj.get();
            quizRank1.setEnergyPoints(quizRank.getEnergyPoints());
            quizRank1.setBadge(quizRank.getBadge());
            quizRankRepository.save(quizRank1);

        }else{
        quizRankRepository.save(quizRank);
    }
        return getBadgeCountsForUser(quizRank.getUserId());
    }

    public BadgeCounts getBadgeCountsForUser(Long userId) {
       Long goldCount = quizRankRepository.countByUserIdAndBadge(userId, 1);
        Long silverCount = quizRankRepository.countByUserIdAndBadge(userId, 2);
        Long bronzeCount = quizRankRepository.countByUserIdAndBadge(userId, 3);

        return new BadgeCounts(userId, goldCount.intValue(), silverCount.intValue(), bronzeCount.intValue());


    }
}
