package com.medivision.medivision.chat.chatroomAndMember.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatroomAndMemberRepository extends JpaRepository<ChatroomAndMember, ChatroomAndMemberId> {
    List<ChatroomAndMember> findDistinctByUserCode(int userCode);
}
