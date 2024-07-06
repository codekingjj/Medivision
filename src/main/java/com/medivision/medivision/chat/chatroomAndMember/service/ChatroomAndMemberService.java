package com.medivision.medivision.chat.chatroomAndMember.service;

import com.medivision.medivision.chat.chatroomAndMember.domain.ChatroomAndMember;
import com.medivision.medivision.chat.chatroomAndMember.domain.ChatroomAndMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatroomAndMemberService {
    private final ChatroomAndMemberRepository chatroomAndMemberRepository;

    public List<ChatroomAndMember> findAllByUserCode(int userCode) {
        return chatroomAndMemberRepository.findDistinctByUserCode(userCode);
    }
}