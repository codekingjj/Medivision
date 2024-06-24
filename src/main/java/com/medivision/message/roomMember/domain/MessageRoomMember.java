package com.medivision.message.roomMember.domain;

import com.websocket.chat.message.room.domain.MessageRoom;
import com.websocket.chat.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "message_room_members")
@IdClass(MessageRoomMemberId.class)
public class MessageRoomMember {
    @Id
    //@ManyToOne
    //@JoinColumn(name = "room_id", referencedColumnName = "roomId")
    //private MessageRoom messageRoom;
    private int roomId;

    @Id
    //@ManyToOne
    //@JoinColumn(name = "user_code", referencedColumnName = "code")
    //private User user;
    private int userCode;

    private Timestamp lastVisitedDate;
    private Timestamp createDate;

    public MessageRoomMember(MessageRoomMemberRequestDto msgRoomMemberDto) {
        this.roomId = msgRoomMemberDto.getRoomId();
        this.userCode = msgRoomMemberDto.getUserCode();
        this.lastVisitedDate = msgRoomMemberDto.getLastVisitedDate();
        this.createDate = msgRoomMemberDto.getCreateDate();
    }
}
