package com.medivision.message.roomMember.domain;

import com.websocket.chat.message.room.domain.MessageRoom;
import com.websocket.chat.message.room.domain.MessageRoomRepository;
import com.websocket.chat.message.room.domain.MessageRoomRequestDto;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.reflect.MemberSignature;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageRoomMemberService {
    private final MessageRoomMemberRepository messageRoomMemberRepository;

    public void addMemberByUserCode(MessageRoomMemberRequestDto msgRoomMemberDto) {
        MessageRoomMember msgRoomMember = new MessageRoomMember(msgRoomMemberDto);
        messageRoomMemberRepository.save(msgRoomMember);
    }

    public void deleteByRoomIdAndUserCode(MessageRoomMemberRequestDto msgRoomMember) {
        messageRoomMemberRepository.deleteByRoomIdAndUserCode(msgRoomMember.getRoomId(), msgRoomMember.getUserCode());
    }
}