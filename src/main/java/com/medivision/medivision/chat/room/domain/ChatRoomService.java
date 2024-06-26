package com.medivision.medivision.chat.room.domain;

import com.medivision.medivision.chat.room.dto.ChatRoomRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> findAllByUserCode(int userCode) {
        return chatRoomRepository.findAllByUserCode(userCode);
    }

    public ChatRoom findByRoomId(int roomId) {
        return chatRoomRepository.findByRoomId(roomId);
    }

    public ChatRoom create(ChatRoomRequestDto chatRoomDto) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.update(chatRoomDto);

        return chatRoomRepository.save(chatRoom);
    }

    public void delete(ChatRoomRequestDto chatRoomDto) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.update(chatRoomDto);

        chatRoomRepository.deleteById(chatRoom.getRoomId());
    }

    public ChatRoom update(ChatRoomRequestDto chatRoomDto) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.update(chatRoomDto);

        return chatRoomRepository.save(chatRoom);
    }
}