package com.medivision.medivision.chat.chatroomMember.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ChatroomMemberRepository extends JpaRepository<ChatroomMember, Integer> {
    boolean existsByRoomId(int roomId);

    List<ChatroomMember> findByUserCode(int userCode);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM chat_room_members cm JOIN chats c ON cm.room_id = c.room_id  WHERE cm.room_id = ?1 AND cm.user_code = ?2 AND c.create_date > cm.last_visited_date")
    int countUnreadChatByRoomIdAndUserCode(int roomId, int userCode);
}
