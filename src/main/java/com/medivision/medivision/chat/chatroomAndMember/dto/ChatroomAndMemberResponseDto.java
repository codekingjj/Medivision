package com.medivision.medivision.chat.chatroomAndMember.dto;

import com.medivision.medivision.chat.chatroomAndMember.domain.ChatroomAndMember;
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
    private Timestamp lastVisitedDate;
    private List<AdminEntity> members;

    public ChatroomAndMemberResponseDto(ChatroomAndMember chatroomAndMember) {
        this.roomId = chatroomAndMember.getRoomId();
        this.name = chatroomAndMember.getName();
        this.lastVisitedDate = chatroomAndMember.getLastVisitedDate();
    }
}