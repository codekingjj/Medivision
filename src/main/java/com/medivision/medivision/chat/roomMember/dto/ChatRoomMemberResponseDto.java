package com.medivision.medivision.chat.roomMember.dto;

import com.medivision.medivision.chat.roomMember.domain.ChatRoomMember;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomMemberResponseDto {
    private  int roomId;
    private  int userCode;

    private  Timestamp lastVisitedDate;
    private  Timestamp createDate;

    public ChatRoomMemberResponseDto(ChatRoomMember chatRoomMember) {
        this.roomId = chatRoomMember.getRoomId();
        this.userCode = chatRoomMember.getUserCode();
        this.lastVisitedDate = chatRoomMember.getLastVisitedDate();
        this.createDate = chatRoomMember.getCreateDate();
    }
}
