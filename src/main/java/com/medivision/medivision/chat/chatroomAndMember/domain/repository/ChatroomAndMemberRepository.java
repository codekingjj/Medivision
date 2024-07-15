package com.medivision.medivision.chat.chatroomAndMember.domain.repository;

import com.medivision.medivision.chat.chatroomAndMember.domain.ChatroomAndMemberId;
import com.medivision.medivision.chat.chatroomAndMember.domain.entity.ChatroomAndMember;
import com.medivision.medivision.chat.chatroomMember.domain.entity.ChatroomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatroomAndMemberRepository extends JpaRepository<ChatroomAndMember, ChatroomAndMemberId> {
    List<ChatroomMember> findByRoomId(int roomId);
    List<ChatroomAndMember> findDistinctByUserCode(int userCode);
}
