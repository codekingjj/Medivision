package com.medivision.medivision.log.report.domain.service;

import com.medivision.medivision.log.report.domain.ReportLogEntity;
import com.medivision.medivision.log.report.dto.ReportLogResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportLogService {

    void reportRead(int reportIndex, String userCode, String ip);
    void reportCreate(int reportIndex);

    ResponseEntity<? super ReportLogResponseDto> reportLog(String userCode);
}
