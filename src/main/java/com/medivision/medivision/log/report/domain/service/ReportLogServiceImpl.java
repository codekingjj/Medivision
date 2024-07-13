package com.medivision.medivision.log.report.domain.service;

import com.medivision.medivision.log.report.domain.ReportLogEntity;
import com.medivision.medivision.log.report.domain.ReportLogRepository;
import com.medivision.medivision.log.report.dto.ReportLogResponseDto;
import com.medivision.medivision.report.domain.service.ReportEntity;
import com.medivision.medivision.report.domain.service.ReportRepository;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportLogServiceImpl implements ReportLogService{
    private final ReportLogRepository reportLogRepository;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Override
    public void reportRead(int reportIndex, String userCode, String ip) {
        int userCodeInt = Integer.parseInt(userCode);
        UserEntity user = userRepository.findByUserCode(userCodeInt);
        String id = user.getUserId();
        LocalDateTime now = LocalDateTime.now();
        String type = "read";

        ReportEntity report = reportRepository.findByReportIndex(reportIndex);
        int studyKey = report.getStudyKey();

        ReportLogEntity reportLog = new ReportLogEntity();
        reportLog.setUserId(id);
        reportLog.setClientIp(ip);
        reportLog.setReportType(type);
        reportLog.setReportIndex(reportIndex);
        reportLog.setStudyKey(studyKey);
        reportLog.setReportDate(now);

        reportLogRepository.save(reportLog);
    }

    @Override
    public void reportCreate(int reportIndex) {
        ReportEntity report = reportRepository.findByReportIndex(reportIndex);
    }

    @Override
    public ResponseEntity<? super ReportLogResponseDto> reportLog(String userCode) {
        int userCodeNumber = Integer.parseInt(userCode);
        List<ReportLogEntity> list = null;
        try {
        UserEntity user = userRepository.findByUserCode(userCodeNumber);
        String userId = user.getUserId();
        list = reportLogRepository.findByUserId(userId);
            System.out.println("list"+list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return ReportLogResponseDto.success(list);
    }
}
