package com.medivision.medivision.chat.chatroomMember.domain.entity;

import com.medivision.medivision.chat.chatroomMember.domain.ChatroomMemberId;
import com.medivision.medivision.chat.chatroomMember.dto.request.ChatroomMemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    private Timestamp lastVisitedDate;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createDate;

    public ChatroomMember(int roomId, int userCode) {
        this.roomId = roomId;
        this.userCode = userCode;
    }

    public ChatroomMember(ChatroomMemberRequestDto chatroomMemberRequestDto) {
        this.roomId = chatroomMemberRequestDto.getRoomId();
        this.userCode = chatroomMemberRequestDto.getUserCode();
        this.lastVisitedDate = chatroomMemberRequestDto.getLastVisitedDate();
    }
}
