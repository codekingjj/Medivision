package com.medivision.medivision.chat.chatroomMember.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ChatroomMemberRequestDto {
    private int roomId;
    private int userCode;

    private Timestamp lastVisitedDate;
    private Timestamp createDate;
}
