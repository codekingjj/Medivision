package com.medivision.medivision.patientBookmark.domain;

import com.medivision.pacs.entity.VStudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientBookmarkRepository extends JpaRepository<PatientBookmark, Integer> {
    List<PatientBookmark> findByUserCode(int userCode);

    @Modifying
    //@Query("DELETE pb.* FROM patient_bookmarks pb WHERE pb.pid IN ?1")
    void deleteByPidIn(List<String> pids);
}
