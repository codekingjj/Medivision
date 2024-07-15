package com.medivision.medivision.chat.chatroom.domain.service;

import com.medivision.medivision.chat.chatroom.domain.entity.Chatroom;
import com.medivision.medivision.chat.chatroom.domain.repository.ChatroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatroomService {
    private final ChatroomRepository chatroomRepository;

    public Chatroom findByRoomId(int roomId) {
        return chatroomRepository.findByRoomId(roomId);
    }

    public Chatroom save(Chatroom chatroom) {
        Chatroom savedChatroom = chatroomRepository.save(chatroom);

        return savedChatroom;
    }

    public void deleteByRoomId(int roomId) {
        chatroomRepository.deleteByRoomId(roomId);
    }

//    public boolean existByRoomIdAndUserCode(int roomId, int userCode) {
//        return chatroomRepository.existsByRoomIdAndUserCode(roomId, userCode);
//    }
}
