package com.medivision.medivision.chat.chatroomAndMember.domain.service;

import com.medivision.medivision.chat.chatroomAndMember.domain.entity.ChatroomAndMember;
import com.medivision.medivision.chat.chatroomAndMember.domain.repository.ChatroomAndMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatroomAndMemberService {
    private final ChatroomAndMemberRepository chatroomAndMemberRepository;

    public void save(ChatroomAndMember chatroomAndMember) {
        chatroomAndMemberRepository.save(chatroomAndMember);
    }

    public List<ChatroomAndMember> findAllByUserCode(int userCode) {
        return chatroomAndMemberRepository.findDistinctByUserCode(userCode);
    }
}