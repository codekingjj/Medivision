package com.medivision.medivision.report.domain.service;

import com.medivision.medivision.report.dto.ReportResponse;
import com.medivision.medivision.report.dto.ReportResponseDto;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.repository.AdminRepository;
import com.medivision.pacs.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final StudyRepository studyRepository;
    private final AdminRepository adminRepository;

    public ResponseEntity<? super ReportResponse> getReportList(int studyKey){
        Long studykey = Long.valueOf(studyKey);
        boolean isExist = studyRepository.existsByStudykey(studykey);
        if(!isExist) return ReportResponse.getListFail();

        List<ReportEntity> list = reportRepository.findByStudyKey(studyKey);
        List<ReportResponseDto> result = new ArrayList<>();
        for(ReportEntity reportEntity : list){
            int writer = reportEntity.getWriter();
            AdminEntity admin = adminRepository.findByUserCode(writer);
            String writerName = admin.getUserName();

            ReportResponseDto report = new ReportResponseDto(reportEntity, writerName);
            result.add(report);
        }

        return ReportResponse.getListSuccess(result);
    }

    public ResponseEntity<? super ReportResponse> getReport(int reportIndex){
        ReportEntity report = reportRepository.findByReportIndex(reportIndex);
        if(report == null) return ReportResponse.getListFail();

        List<ReportResponseDto> result = new ArrayList<>();
        int writer = report.getWriter();
        AdminEntity admin = adminRepository.findByUserCode(writer);
        String writerName = admin.getUserName();

        ReportResponseDto target = new ReportResponseDto(report, writerName);
        result.add(target);

        return ReportResponse.getReportSuccess(result);
    }

}
