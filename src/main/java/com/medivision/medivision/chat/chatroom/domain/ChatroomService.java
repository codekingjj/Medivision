package com.medivision.medivision.chat.chatroom.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
