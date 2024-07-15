package com.medivision.medivision.patientBookmark.domain.repository;

import com.medivision.medivision.patientBookmark.domain.entity.PatientBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientBookmarkRepository extends JpaRepository<PatientBookmark, Integer> {
    List<PatientBookmark> findByUserCode(int userCode);

    PatientBookmark findByUserCodeAndPid(int userCode, String pid);

    @Modifying
    //@Query("DELETE pb.* FROM patient_bookmarks pb WHERE pb.pid IN ?1")
    void deleteByPidIn(List<String> pids);
}
