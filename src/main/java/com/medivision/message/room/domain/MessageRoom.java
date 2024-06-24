package com.medivision.message.room.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "message_rooms")
public class MessageRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private String name;

    private Timestamp createDate;

    public void update(MessageRoomRequestDto msgRoomDto) {
        this.roomId = msgRoomDto.getRoomId();
        this.name = msgRoomDto.getName();
    }
}
