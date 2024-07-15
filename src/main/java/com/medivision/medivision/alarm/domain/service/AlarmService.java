package com.medivision.medivision.alarm.domain.service;

import com.medivision.medivision.alarm.dto.AlarmReponseDto;
//import com.medivision.medivision.chat.dto.ChatRequestDto;
import com.medivision.medivision.chat.chat.domain.entity.Chat;
import org.springframework.http.ResponseEntity;

public interface AlarmService {
    void saveStudy(int studykey);
    void saveChat(Chat chatRequestDto);

    void checkAlarm(String index);
    ResponseEntity<? super AlarmReponseDto> getAlarmList(String userCode);
}
