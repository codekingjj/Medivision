package com.medivision.message.roomMember.domain;

import com.websocket.chat.message.room.domain.MessageRoom;
import com.websocket.chat.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class MessageRoomMemberResponseDto {
    private int messageRoomId;
    private int userCode;

    private Timestamp lastVisitedDate;
    private Timestamp createDate;

    public MessageRoomMemberResponseDto(MessageRoomMember messageRoomMember) {
        this.messageRoomId = messageRoomMember.getRoomId();
        this.userCode = messageRoomMember.getUserCode();
        this.lastVisitedDate = messageRoomMember.getLastVisitedDate();
        this.createDate = messageRoomMember.getCreateDate();
    }
}
