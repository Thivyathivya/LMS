package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.QuizRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRankRepository extends JpaRepository<QuizRank,Integer> {
    Optional<QuizRank> findByUserIdAndSectionId(Long userId, Integer sectionId);
}
