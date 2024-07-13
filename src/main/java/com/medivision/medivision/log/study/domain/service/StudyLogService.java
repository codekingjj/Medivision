package com.medivision.medivision.log.study.domain.service;

public interface StudyLogService {
    void saveStudyLog(String userCode, String studyKey, String ip);
}
