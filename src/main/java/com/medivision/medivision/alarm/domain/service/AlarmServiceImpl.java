package com.medivision.medivision.alarm.domain.service;

import com.medivision.common.ResponseDto;
import com.medivision.medivision.alarm.domain.AlarmEntity;
import com.medivision.medivision.alarm.domain.AlarmRepository;
import com.medivision.medivision.alarm.dto.AlarmReponseDto;
//import com.medivision.medivision.chat.dto.ChatRequestDto;
//import com.medivision.medivision.chat.room.domain.ChatRoom;
//import com.medivision.medivision.chat.room.domain.ChatRoomRepository;
//import com.medivision.medivision.chat.roomMember.domain.ChatRoomMember;
//import com.medivision.medivision.chat.roomMember.domain.ChatRoomMemberRepository;
import com.medivision.medivision.chat.chat.domain.entity.Chat;
import com.medivision.medivision.chat.chatroomAndMember.domain.entity.ChatroomAndMember;
import com.medivision.medivision.chat.chatroomAndMember.domain.repository.ChatroomAndMemberRepository;
import com.medivision.medivision.chat.chatroomMember.domain.entity.ChatroomMember;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.AdminRepository;
import com.medivision.medivision.user.domain.repository.UserRepository;
import com.medivision.medivision.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{

    private final AdminRepository adminRepository;
    private final AlarmRepository alarmRepository;
    private final ChatroomAndMemberRepository chatRoomMemberRepository;
    @Override
    public void saveStudy(int studykey) {

    }

    @Override
    public void saveChat(Chat chat) {

        List<ChatroomAndMember> chatRoomMemberList = chatRoomMemberRepository.findByRoomId(chat.getRoomId());
//
        LocalDateTime date = LocalDateTime.now();
        for(ChatroomAndMember member: chatRoomMemberList){
            if(member.getUserCode() == chat.getSenderUserCode()) continue;
            String content = "";
            AdminEntity user = adminRepository.findByUserCode(member.getUserCode());
            String userId = user.getUserName();
            content+= "【"+userId+"】님이 "+ "메세지를 보내셨습니다.";
            content+= "<br>『"+chat.getMessage()+"』";
            AlarmEntity alarm = new AlarmEntity();
            alarm.setContent(content);
            alarm.setUserCode(member.getUserCode());
            alarm.setRegDate(date);
            alarmRepository.save(alarm);
        }
//        AdminEntity user = adminRepository.findByUserCode(chat.getSenderUserCode());
//
//        String content = "";
//        String userId = user.getUserName();
//        content+= "【"+userId+"】님이 "+ "메세지를 보내셨습니다.";
//        content+= "<br>『"+chat.getMessage()+"』";
//        LocalDateTime date = LocalDateTime.now();
//
//        AlarmEntity alarm = new AlarmEntity();
//        alarm.setContent(content);
//        alarm.setUserCode(chat.getSenderUserCode());
//        alarmRepository.save(alarm);
    }

    @Override
    public void checkAlarm(String index) {
        int alarmIndex = Integer.parseInt(index);
        AlarmEntity alarm = alarmRepository.findByAlarmIndex(alarmIndex);
        alarm.setCheck(true);
        alarmRepository.save(alarm);
    }

    @Override
    public ResponseEntity<? super AlarmReponseDto> getAlarmList(String userCode) {
        int userCodeNumber = Integer.parseInt(userCode);
        System.out.println("codeTemp: " + userCodeNumber);

        List<AlarmEntity> list = alarmRepository.findByUserCodeOrderByRegDateDesc(userCodeNumber);
        System.out.println("list: " + list);
        if (list == null) return ResponseDto.databaseError();
        int count = 0;
        for(AlarmEntity alarm : list){
            if(!alarm.isCheck())
                count++;
        }
        return AlarmReponseDto.success(list, count);
    }

}
