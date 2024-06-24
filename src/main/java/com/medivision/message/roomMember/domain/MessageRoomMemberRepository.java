package com.medivision.message.roomMember.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MessageRoomMemberRepository extends JpaRepository<MessageRoomMember, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE msgRoomMem.* FROM message_room_members msgRoomMem JOIN message_rooms msgRoom ON msgRoomMem.room_id = msgRoom.room_id WHERE msgRoomMem.room_Id =:roomId AND msgRoomMem.user_code =:userCode", nativeQuery = true)
    //@Query(value = "DELETE msgRoomMem FROM MessageRoomMember msgRoomMem", nativeQuery = true)
    void deleteByRoomIdAndUserCode(int roomId, int userCode);
}
