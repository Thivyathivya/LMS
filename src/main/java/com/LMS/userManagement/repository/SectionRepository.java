package com.LMS.userManagement.repository;

import com.LMS.userManagement.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface SectionRepository extends JpaRepository<Section, UUID> {


}
