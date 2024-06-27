package com.medivision.medivision.patientBookmark.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientBookmarkRepository extends JpaRepository<PatientBookmark,Integer> {
    List<PatientBookmark> findByUserCode(int userCode);
}
