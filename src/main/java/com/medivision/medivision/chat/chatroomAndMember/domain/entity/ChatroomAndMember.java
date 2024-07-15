package com.medivision.medivision.chat.chatroomAndMember.domain.entity;

import com.medivision.medivision.chat.chatroomAndMember.domain.ChatroomAndMemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@IdClass(ChatroomAndMemberId.class)
@Table(name = "chat_room_and_members", schema = "chat")
public class ChatroomAndMember {
    @Id
    private int roomId;

    @Id
    private int userCode;

    private String name;

    private Timestamp lastVisitedDate;
}
