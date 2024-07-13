package com.medivision.medivision.log.study.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyLogRepository extends JpaRepository<StudyLogEntity, Integer> {
    List<StudyLogEntity> findByUserId(String userId);
}
