package com.medivision.medivision.alarm.domain.service;

import com.medivision.medivision.chat.dto.ChatRequestDto;

public interface AlarmService {
    void saveStudy(int studykey);
    void saveChat(ChatRequestDto chatRequestDto);
}
