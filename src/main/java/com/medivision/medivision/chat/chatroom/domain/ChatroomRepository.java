package com.medivision.medivision.chat.chatroom.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Integer> {
    //public boolean existsByRoomIdAndUserCode(int roomId, int userCode);
}
