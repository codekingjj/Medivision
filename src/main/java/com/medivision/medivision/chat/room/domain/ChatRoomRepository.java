package com.medivision.medivision.chat.room.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    @Query(value = "SELECT chat_rooms.* FROM chat_rooms JOIN chat_room_members ON chat_rooms.room_id = chat_room_members.room_id WHERE chat_room_members.user_code =:userCode", nativeQuery = true)
    List<ChatRoom> findAllByUserCode(int userCode);

    @Query(value = "SELECT chat_rooms.* FROM chat_rooms WHERE chat_rooms.room_id =:roomId", nativeQuery = true)
    ChatRoom findByRoomId(int roomId);
}
