package com.medivision.medivision.chat.roomMember.domain;

import com.medivision.medivision.chat.roomMember.dto.ChatRoomMemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@IdClass(ChatRoomMemberId.class)
@Table(name = "chat_room_members")
public class ChatRoomMember {
    //@ManyToOne
    //@JoinColumn(name = "room_id", referencedColumnName = "roomId")
    //private MessageRoom messageRoom;
    @Id
    @Column(name = "room_id")
    private  int roomId;

    //@ManyToOne
    //@JoinColumn(name = "user_code", referencedColumnName = "code")
    //private User user;
    @Id
    @Column(name = "user_code")
    private  int userCode;

    @CreationTimestamp
    private  Timestamp lastVisitedDate;

    @CreationTimestamp
    private  Timestamp createDate;

    public ChatRoomMember(ChatRoomMemberRequestDto chatRoomMemberDto) {
        this.roomId = chatRoomMemberDto.getRoomId();
        this.userCode = chatRoomMemberDto.getUserCode();
        this.lastVisitedDate = chatRoomMemberDto.getLastVisitedDate();
        this.createDate = chatRoomMemberDto.getCreateDate();
    }
}
