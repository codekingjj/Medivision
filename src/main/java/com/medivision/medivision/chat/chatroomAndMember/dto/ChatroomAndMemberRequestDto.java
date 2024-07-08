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
public class ChatroomAndMemberRequestDto {
    private int roomId;
    private int creatorUserCode;
    private String name;
    private Timestamp lastVisitedDate;
    private List<AdminEntity> members;
}