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
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.AdminRepository;
import com.medivision.medivision.user.domain.repository.UserRepository;
import com.medivision.medivision.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{

    private final AdminRepository adminRepository;
    private final AlarmRepository alarmRepository;
//    private final ChatRoomMemberRepository chatRoomMemberRepository;
    @Override
    public void saveStudy(int studykey) {

    }

//    @Override
//    public void saveChat(ChatRequestDto chatRequestDto) {
//
//        List<ChatRoomMember> chatRoomMemberList = chatRoomMemberRepository.findByRoomId(chatRequestDto.getRoomId());
//
//        for(ChatRoomMember member: chatRoomMemberList){
//            if(member.getUserCode() == chatRequestDto.getSenderUserCode()) continue;
//            String content = "";
//            AdminEntity user = adminRepository.findByUserCode(member.getUserCode());
//            String userId = user.getUserName();
//            content+= "【"+userId+"】님이 "+ "메세지를 보내셨습니다.";
//            content+= "<br>『"+chatRequestDto.getMessage()+"』";
//            AlarmEntity alarm = new AlarmEntity();
//            alarm.setContent(content);
//            alarm.setUserCode(member.getUserCode());
//            alarmRepository.save(alarm);
//        }
//    }

    @Override
    public ResponseEntity<? super AlarmReponseDto> getAlarmList(String userCode) {
        int userCodeNumber = Integer.parseInt(userCode);
        System.out.println("codeTemp: " + userCodeNumber);

        List<AlarmEntity> list = alarmRepository.findByUserCode(userCodeNumber);
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
