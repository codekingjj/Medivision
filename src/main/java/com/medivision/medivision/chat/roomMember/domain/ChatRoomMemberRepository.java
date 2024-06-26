package com.medivision.medivision.chat.roomMember.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, ChatRoomMemberId> {
    @Modifying
    @Transactional
    @Query(value = "DELETE chatRoomMem.* FROM chat_room_members chatRoomMem JOIN chat_rooms chatRoom ON chatRoomMem.room_id = chatRoom.room_id WHERE chatRoomMem.room_Id =:roomId AND chatRoomMem.user_code =:userCode", nativeQuery = true)
    void deleteByRoomIdAndUserCode(int roomId, int userCode);
}
