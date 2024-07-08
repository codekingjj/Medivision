package com.medivision.medivision.chat.chatroomMember.domain;

import com.medivision.medivision.chat.chatroomMember.dto.ChatroomMemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatroomMemberService {
    private final ChatroomMemberRepository chatroomMemberRepository;

    public List<ChatroomMember> findByUserCode(int userCode) {
        return chatroomMemberRepository.findByUserCode(userCode);
    }

    public int countUnreadChatByRoomIdAndUserCode(int roomId, int userCode) {
        return chatroomMemberRepository.countUnreadChatByRoomIdAndUserCode(roomId, userCode);
    }

    public void save(ChatroomMember chatroomMember) {
        chatroomMemberRepository.save(chatroomMember);
    }

    public void delete(ChatroomMember chatroomMember) {
        chatroomMemberRepository.delete(chatroomMember);
    }

    public boolean existByChatroomId(int roomId) {
        return chatroomMemberRepository.existsByRoomId(roomId);
    }
}
