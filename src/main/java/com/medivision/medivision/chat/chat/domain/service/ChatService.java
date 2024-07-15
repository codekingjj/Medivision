package com.medivision.medivision.chat.chat.domain.service;

import com.medivision.medivision.chat.chat.domain.repository.ChatRepository;
import com.medivision.medivision.chat.chat.domain.entity.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.medivision.medivision.chat.chat.utils.ChatPage;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public void save(Chat chat) {
        chatRepository.save(chat);
    }

    public Page<Chat> findByRoomIdOrderByCreateDateDesc(int roomId, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, ChatPage.ITEM_SIZE);

        return chatRepository.findByRoomIdOrderByCreateDateDesc(roomId, pageable);
    }

    public Chat findLatestChatByRoomId(int roomId) {
        return chatRepository.findFirstByRoomIdOrderByCreateDateDesc(roomId);
    }

    public boolean existsByRoomId(int roomId) {
        return chatRepository.existsByRoomId(roomId);
    }
}
