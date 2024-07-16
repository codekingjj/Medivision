package com.medivision.medivision.log.admin.domain;

import com.medivision.medivision.log.login.domain.LoginLogEntity;
import com.medivision.medivision.log.login.domain.LoginLogRepository;
import com.medivision.medivision.log.report.domain.ReportLogEntity;
import com.medivision.medivision.log.report.domain.ReportLogRepository;
import com.medivision.medivision.log.study.domain.StudyLogEntity;
import com.medivision.medivision.log.study.domain.StudyLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLogServiceImpl implements AdminLogService {

    private final LoginLogRepository loginLogRepository;
    private final StudyLogRepository studyLogRepository;
    private final ReportLogRepository reportLogRepository;
    @Override
    public Page<LoginLogEntity> loginList(int pageNum, int pageSize) {
        Page<LoginLogEntity> loginList = loginLogRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
        if(loginList == null) return null;
        return loginList;
    }

    @Override
    public long getTotalCountLogin() {
        return loginLogRepository.count();
    }

    @Override
    public Page<StudyLogEntity> studyList(int pageNum, int pageSize) {
        Page<StudyLogEntity> studyList = studyLogRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
        if(studyList == null) return null;
        return studyList;
    }

    @Override
    public long getTotalCountStudy() {
        return studyLogRepository.count();
    }

    @Override
    public Page<ReportLogEntity> reportList(int pageNum, int pageSize) {
        Page<ReportLogEntity> reportList = reportLogRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
        if(reportList == null) return null;
        return reportList;
    }

    @Override
    public long getTotalCountReport() {
        return reportLogRepository.count();
    }
}
