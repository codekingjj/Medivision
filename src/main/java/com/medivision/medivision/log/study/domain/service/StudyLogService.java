package com.medivision.medivision.log.study.domain.service;

public interface StudyLogService {
    void saveStudyLog(String userCode, int studyKey, String ip);
}
