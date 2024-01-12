package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.SubSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SubSectionRepository extends JpaRepository<SubSection,Integer> {
}
