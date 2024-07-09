package com.medivision.medivision.chat.chatroomAndMember.domain;

import com.medivision.medivision.chat.chatroomMember.domain.ChatroomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatroomAndMemberRepository extends JpaRepository<ChatroomAndMember, ChatroomAndMemberId> {
    public List<ChatroomAndMember> findDistinctByUserCode(int userCode);
    List<ChatroomMember> findByRoomId(int roomId);
    List<ChatroomAndMember> findDistinctByUserCode(int userCode);
}
