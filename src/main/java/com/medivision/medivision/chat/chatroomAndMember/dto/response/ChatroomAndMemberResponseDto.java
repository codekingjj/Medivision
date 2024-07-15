package com.medivision.medivision.chat.chatroomAndMember.dto.response;

import com.medivision.medivision.chat.chatroomAndMember.domain.entity.ChatroomAndMember;
import com.medivision.medivision.user.domain.entity.AdminEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ChatroomAndMemberResponseDto {
    private int roomId;
    private String name;
    private String latestMessage;
    private Timestamp latestMessageSendDate;
    private Timestamp lastVisitedDate;
    private List<AdminEntity> members;
    private int numOfUnreadMessages;

    public ChatroomAndMemberResponseDto(ChatroomAndMember chatroomAndMember) {
        this.roomId = chatroomAndMember.getRoomId();
        this.name = chatroomAndMember.getName();
        this.lastVisitedDate = chatroomAndMember.getLastVisitedDate();
    }
}