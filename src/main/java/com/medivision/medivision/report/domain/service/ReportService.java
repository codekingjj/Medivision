package com.medivision.medivision.report.domain.service;

import com.medivision.medivision.decode.domain.entity.DecodeEntity;
import com.medivision.medivision.decode.domain.repository.DecodeRepository;
import com.medivision.medivision.report.dto.ReportRequestDto;
import com.medivision.medivision.report.dto.ReportResponse;
import com.medivision.medivision.report.dto.ReportResponseDto;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.repository.AdminRepository;
import com.medivision.medivision.user.domain.repository.UserRepository;
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
    private final UserRepository userRepository;

    public ResponseEntity<? super ReportResponse> getReportList(ReportRequestDto reportDto){
        int userCode = reportDto.getWriter();

        int studyKey = reportDto.getStudyKey();
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

        return ReportResponse.getListSuccess(result,userCode);
    }

    public String getUsername(int userCode){
        AdminEntity admin = adminRepository.findByUserCode(userCode);
        return admin.getUserName();
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

    public ReportResponseDto getTarget(int reportIndex){
        ReportEntity report = reportRepository.findByReportIndex(reportIndex);
        if(report == null) return null;

        int writer = report.getWriter();
        AdminEntity admin = adminRepository.findByUserCode(writer);
        String writerName = admin.getUserName();
        System.out.println("writerName : "+writerName);
        ReportResponseDto target = new ReportResponseDto(report, writerName);
        return target;
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
        int writer = reportRequestDto.getWriter();
        List<ReportEntity> list = reportRepository.findByStudyKey(studyKey);
        for(ReportEntity reportEntity : list){
            String typeDecode = reportEntity.getTypeDecode();
            if("예비판독".equals(typeDecode)){
                return false;
            }else if(reportEntity.getWriter() == writer)
                return false;
        }

        return true;
    }

    private boolean checkReportList(ReportRequestDto reportRequestDto){
        int studyKey = reportRequestDto.getStudyKey();
        int writer = reportRequestDto.getWriter();
        List<ReportEntity> list = reportRepository.findByStudyKey(studyKey);

        int cnt = 0;
        for(ReportEntity reportEntity : list){
            String typeDecode = reportEntity.getTypeDecode();

            if(reportEntity.getWriter() == writer)
                return false;

            if("판독".equals(typeDecode)){
                cnt ++;
            }
        }

        if(cnt == 2)return false;

        return true;
    }

    private boolean  checkUpdateSpareReport(ReportRequestDto reportDto){
        int studyKey = reportDto.getStudyKey();
        int writer = reportDto.getWriter();

        List<ReportEntity> list = reportRepository.findByStudyKey(studyKey);
        for(ReportEntity reportEntity : list) {
            String typeDecode = reportEntity.getTypeDecode();
            if("예비판독".equals(typeDecode) && writer != reportEntity.getWriter()){
                return false;
            }
            else if("판독".equals(typeDecode) && writer != reportEntity.getWriter())
                return false;
        }

        return true;
    }

    private boolean checkUpdateReport(ReportRequestDto reportDto){
        int studyKey = reportDto.getStudyKey();
        int writer = reportDto.getWriter();

        List<ReportEntity> list = reportRepository.findByStudyKey(studyKey);
        int cnt = 0;
        for(ReportEntity reportEntity : list) {
            String typeDecode = reportEntity.getTypeDecode();
            if("판독".equals(typeDecode) && writer != reportEntity.getWriter())
                cnt++;
        }

        if(cnt == 2) return false;

        return true;
    }

    public ResponseEntity<? super ReportResponse>  updateReport(ReportRequestDto reportDto, int index){
        ReportEntity report = reportRepository.findByReportIndex(index);
        if(report.getWriter() != reportDto.getWriter()) return ReportResponse.differentUser();
        if(!report.getTypeDecode().equals(reportDto.getTypeDecode())){
            boolean isValid = true;
            if("예비판독".equals(reportDto.getTypeDecode())){
                isValid = checkUpdateSpareReport(reportDto);
            }else{
                isValid = checkUpdateReport(reportDto);
            }

            if(!isValid) return ReportResponse.updateFail();
            report.update(reportDto);
            reportRepository.save(report);
            int studyKey = reportDto.getStudyKey();
            DecodeEntity decode = decodeRepository.findByStudyKey(studyKey);

            String decodeStatus =report.getTypeDecode()+" 완료";
            decode = new DecodeEntity(decode.getStudyKey(),decodeStatus);
            decodeRepository.save(decode);
        }
        else{
            report.update(reportDto);
            reportRepository.save(report);
        }

        return ReportResponse.updateSuccess();
    }

}
