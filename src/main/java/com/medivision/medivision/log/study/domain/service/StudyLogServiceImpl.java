package com.medivision.medivision.log.study.domain.service;

import com.medivision.medivision.log.study.domain.StudyLogEntity;
import com.medivision.medivision.log.study.domain.StudyLogRepository;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.UserRepository;
import com.medivision.pacs.entity.StudyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudyLogServiceImpl implements StudyLogService{

    private final UserRepository userRepository;
    private final StudyLogRepository studyLogRepository;

    @Override
    public void saveStudyLog(String userCode, int studyKey, String ip) {
        int userCodeNumber = Integer.parseInt(userCode);
        UserEntity user = userRepository.findByUserCode(userCodeNumber);
        String userId = user.getUserId();
        LocalDateTime now = LocalDateTime.now();

        StudyLogEntity studyLogEntity = new StudyLogEntity();
        studyLogEntity.setUserId(userId);
        studyLogEntity.setStudyKey(studyKey);
        studyLogEntity.setClientIp(ip);
        studyLogEntity.setOpenDate(now);

        studyLogRepository.save(studyLogEntity);
    }
}
