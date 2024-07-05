package com.medivision.medivision.chat.chatroomMember.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@IdClass(ChatroomMemberId.class)
@Table(name = "chat_room_members", schema = "chat")
public class ChatroomMember {
    @Id
    private int roomId;

    @Id
    private int userCode;

    private Timestamp lastVisitedDate;
    private Timestamp createDate;
}
