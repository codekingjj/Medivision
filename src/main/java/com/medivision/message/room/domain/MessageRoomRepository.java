package com.medivision.message.room.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRoomRepository extends JpaRepository<MessageRoom, Integer> {
    //@Query("SELECT msgRoom FROM MessageRoom msgRoom JOIN msgRoom.roomId JOIN MessageRoomMember.message.roomId WHERE MessageRoomMember.message.senderUserCode =:userCode")
    //public List<MessageRoom> findAllByUserCode(int userCode);
}
