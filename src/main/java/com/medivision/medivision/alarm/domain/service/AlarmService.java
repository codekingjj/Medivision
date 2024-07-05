package com.medivision.medivision.alarm.domain.service;

import com.medivision.medivision.alarm.dto.AlarmReponseDto;
//import com.medivision.medivision.chat.dto.ChatRequestDto;
import org.springframework.http.ResponseEntity;

public interface AlarmService {
    void saveStudy(int studykey);
//    void saveChat(ChatRequestDto chatRequestDto);

    ResponseEntity<? super AlarmReponseDto> getAlarmList(String userCode);
}
