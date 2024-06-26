package com.medivision.medivision.chat.roomMember.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomMemberRequestDto {
    @Id
    private int roomId;

    @Id
    private int userCode;

    private Timestamp lastVisitedDate;
    private Timestamp createDate;
}
