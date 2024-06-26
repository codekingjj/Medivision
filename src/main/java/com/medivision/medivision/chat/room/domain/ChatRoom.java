package com.medivision.medivision.chat.room.domain;

import com.medivision.medivision.chat.room.dto.ChatRoomRequestDto;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "chat_rooms")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private String name;

    @CreationTimestamp
    private Timestamp createDate;

    public void update(ChatRoomRequestDto msgRoomDto) {
        this.roomId = msgRoomDto.getRoomId();
        this.name = msgRoomDto.getName();
    }
}
