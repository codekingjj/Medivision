package com.medivision.pacs.repository;

import com.medivision.pacs.entity.PatientEntity;
import com.medivision.pacs.entity.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, String> {
    PatientEntity findByPid(String pid);

    List<PatientEntity> findByPidContainingOrPnameContaining(String pid, String pname);

    List<PatientEntity> findByPidContaining(String pid);

    List<PatientEntity> findByPnameContaining(String pname);
}