package com.medivision.medivision.report.domain.service;

import com.medivision.medivision.decode.domain.entity.DecodeEntity;
import com.medivision.medivision.decode.domain.repository.DecodeRepository;
import com.medivision.medivision.report.dto.ReportRequestDto;
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
    private final DecodeRepository decodeRepository;

    public ResponseEntity<? super ReportResponse> getReportList(int studyKey){
        Long studykey = Long.valueOf(studyKey);
        boolean isExist = studyRepository.existsByStudykey(studykey);
        if(!isExist) return ReportResponse.getListFail(); //수정 메소드 들어가서

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
        if(report == null) return ReportResponse.getReportFail();  //수정 메소드 들어가서

        List<ReportResponseDto> result = new ArrayList<>();
        int writer = report.getWriter();
        AdminEntity admin = adminRepository.findByUserCode(writer);
        String writerName = admin.getUserName();

        ReportResponseDto target = new ReportResponseDto(report, writerName);
        result.add(target);

        return ReportResponse.getReportSuccess(result);
    }

    public ResponseEntity<? super ReportResponse> createReport(ReportRequestDto reportDto){
        int writer = reportDto.getWriter();
        AdminEntity admin = adminRepository.findByUserCode(writer);
        if(admin == null || admin.getUserLicensenum() == null) return ReportResponse.getListFail(); //수정 메소드 들어가서

        String typeDecode = reportDto.getTypeDecode();

        if("예비판독".equals(typeDecode)){
            if(!checkSpareReport(reportDto)) return ReportResponse.createSpareReportFail(); //수정
        }else if("판독".equals(typeDecode)){
            if(!checkReportList(reportDto)) return ReportResponse.createReportFail(); //수정
        }

        ReportEntity reportEntity = new ReportEntity(reportDto);
        reportRepository.save(reportEntity);

        int studyKey = reportDto.getStudyKey();
        DecodeEntity decode = decodeRepository.findByStudyKey(studyKey);

        String decodeStatus = typeDecode+" 완료";
        decode = new DecodeEntity(decode.getStudyKey(),decodeStatus);
        decodeRepository.save(decode);
        return ReportResponse.createReportSuccess();
    }

    private boolean checkSpareReport(ReportRequestDto reportRequestDto){
        int studyKey = reportRequestDto.getStudyKey();
        List<ReportEntity> list = reportRepository.findByStudyKey(studyKey);
        for(ReportEntity reportEntity : list){
            String typeDecode = reportEntity.getTypeDecode();
            if("예비판독".equals(typeDecode)){
                return false;
            }
        }

        return true;
    }

    private boolean checkReportList(ReportRequestDto reportRequestDto){
        int studyKey = reportRequestDto.getStudyKey();
        List<ReportEntity> list = reportRepository.findByStudyKey(studyKey);

        int cnt = 0;
        for(ReportEntity reportEntity : list){
            String typeDecode = reportEntity.getTypeDecode();
            if("판독".equals(typeDecode)){
                cnt ++;
            }
        }

        if(cnt == 2)return false;

        return true;
    }

}
