package com.medivision.pacs.repository;

import com.medivision.pacs.entity.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity,Long> {

    List<StudyEntity> findAll();
    boolean existsByStudykey(Long studykey);
}