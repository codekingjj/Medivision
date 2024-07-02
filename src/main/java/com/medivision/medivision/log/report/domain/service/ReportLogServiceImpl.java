package com.medivision.medivision.log.report.domain.service;

import com.medivision.medivision.log.report.domain.ReportLogEntity;
import com.medivision.medivision.report.domain.service.ReportEntity;
import com.medivision.medivision.report.domain.service.ReportRepository;
import com.medivision.medivision.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportLogServiceImpl implements ReportLogService{
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    @Override
    public void reportRead(int reportIndex) {
        ReportEntity report = reportRepository.findByReportIndex(reportIndex);
        ReportLogEntity reportLog = new ReportLogEntity();

//        reportLog.setUserId();
    }

    @Override
    public void reportCreate(int reportIndex) {
        ReportEntity report = reportRepository.findByReportIndex(reportIndex);

    }
}
