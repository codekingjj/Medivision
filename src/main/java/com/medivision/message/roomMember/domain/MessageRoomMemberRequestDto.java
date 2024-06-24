package com.medivision.message.roomMember.domain;

import com.websocket.chat.message.room.domain.MessageRoom;
import com.websocket.chat.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class MessageRoomMemberRequestDto {
    @Id
    private int roomId;

    @Id
    private int userCode;

    private Timestamp lastVisitedDate;
    private Timestamp createDate;
}
