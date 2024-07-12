package com.medivision.pacs.repository;

import com.medivision.pacs.entity.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity,Integer> {

    List<StudyEntity> findAll();
    boolean existsByStudykey(int studykey);
    StudyEntity findById(int studykey);
}