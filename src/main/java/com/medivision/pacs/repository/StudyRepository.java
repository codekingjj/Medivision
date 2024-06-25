package com.medivision.pacs.repository;

import com.medivision.pacs.entity.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity,Long> {

    public List<StudyEntity> findAll();
    public boolean existsByStudykey(Long studykey);
}