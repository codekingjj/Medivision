package com.medivision.medivision.report.domain.service;

import com.medivision.medivision.report.dto.ReportRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity,Integer> {
    ReportEntity findByReportIndex(int reportIndex);
    List<ReportEntity> findByStudyKey(int studyKey);
}
