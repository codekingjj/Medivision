package com.medivision.medivision.chat.roomMember.domain;

import com.medivision.medivision.chat.roomMember.dto.ChatRoomMemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomMemberService {
    private final ChatRoomMemberRepository chatRoomMemberRepository;

    public ChatRoomMember addMemberByUserCode(ChatRoomMemberRequestDto chatRoomMemberDto) {
        ChatRoomMember chatRoomMember = new ChatRoomMember(chatRoomMemberDto);

        return chatRoomMemberRepository.save(chatRoomMember);
    }

    public List<ChatRoomMember> addMembersByUserCode(List<ChatRoomMemberRequestDto> chatRoomMemberDtos) {
        List<ChatRoomMember> chatRoomMembers = new ArrayList<>();

        for (ChatRoomMemberRequestDto chatRoomMemberDto : chatRoomMemberDtos) {
            ChatRoomMember chatRoomMember = addMemberByUserCode(chatRoomMemberDto);
            chatRoomMembers.add(chatRoomMember);
        }

        return chatRoomMembers;
    }

    public void deleteByRoomIdAndUserCode(ChatRoomMemberRequestDto chatRoomMember) {
        chatRoomMemberRepository.deleteByRoomIdAndUserCode(chatRoomMember.getRoomId(), chatRoomMember.getUserCode());
    }
}