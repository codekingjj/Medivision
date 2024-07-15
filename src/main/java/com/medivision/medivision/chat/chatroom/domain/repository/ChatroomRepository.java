package com.medivision.medivision.chat.chatroom.domain.repository;

import com.medivision.medivision.chat.chatroom.domain.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Integer> {
    Chatroom findByRoomId(int roomId);

    @Transactional
    void deleteByRoomId(int roomId);
}
