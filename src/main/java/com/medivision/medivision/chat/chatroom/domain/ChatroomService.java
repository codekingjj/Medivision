package com.medivision.medivision.chat.chatroom.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatroomService {
    private final ChatroomRepository chatroomRepository;

    public List<Chatroom> findAll() {
        return chatroomRepository.findAll();
    }

//    public boolean existByRoomIdAndUserCode(int roomId, int userCode) {
//        return chatroomRepository.existsByRoomIdAndUserCode(roomId, userCode);
//    }
}
