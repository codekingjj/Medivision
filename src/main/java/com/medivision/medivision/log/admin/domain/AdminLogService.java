package com.medivision.medivision.log.admin.domain;

import com.medivision.medivision.log.login.domain.LoginLogEntity;
import com.medivision.medivision.log.report.domain.ReportLogEntity;
import com.medivision.medivision.log.study.domain.StudyLogEntity;
import org.springframework.data.domain.Page;

public interface AdminLogService {

    Page<LoginLogEntity> loginList(int pageNum, int pageSize);

    long getTotalCountLogin();

    Page<StudyLogEntity> studyList(int pageNum, int pageSize);

    long getTotalCountStudy();

    Page<ReportLogEntity> reportList(int pageNum, int pageSize);

    long getTotalCountReport();
}
