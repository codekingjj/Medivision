package com.medivision.medivision.alarm.domain.service;

import com.medivision.medivision.alarm.domain.AlarmEntity;
import com.medivision.medivision.alarm.domain.AlarmRepository;
import com.medivision.medivision.chat.dto.ChatRequestDto;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.AdminRepository;
import com.medivision.medivision.user.domain.repository.UserRepository;
import com.medivision.medivision.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{

    private final AdminRepository adminRepository;
    private final AlarmRepository alarmRepository;
    @Override
    public void saveStudy(int studykey) {

    }

    @Override
    public void saveChat(ChatRequestDto chatRequestDto) {
        String content = "";
        AdminEntity user = adminRepository.findByUserCode(chatRequestDto.getSenderUserCode());
        String userId = user.getUserName();
        content+= "【"+userId+"】님이 "+ "메세지를 보내셨습니다.";
        content+= "<br>『"+chatRequestDto.getMessage()+"』";
        AlarmEntity alarm = new AlarmEntity();
        alarm.setContent(content);
        alarm.setUserCode(chatRequestDto.getSenderUserCode());
        alarmRepository.save(alarm);
    }
}
