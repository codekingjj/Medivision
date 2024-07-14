package com.medivision.medivision.log.study.domain.service;

import com.medivision.medivision.log.study.dto.StudyKeyLogResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudyLogService {
    void saveStudyLog(String userCode, String studyKey, String ip);

    ResponseEntity<? super StudyKeyLogResponseDto> studyLog(String userCode);
}
