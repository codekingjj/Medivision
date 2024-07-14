package com.medivision.medivision.log.report.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportLogRepository extends JpaRepository<ReportLogEntity, Integer> {

    List<ReportLogEntity> findByUserId(String userId);
}
