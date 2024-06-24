package com.medivision.message.room.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class MessageRoomResponseDto {
    private int roomId;
    private String name;
    private Timestamp createDate;

    public MessageRoomResponseDto(MessageRoom messageRoom) {
        this.roomId = messageRoom.getRoomId();
        this.name = messageRoom.getName();
        this.createDate = messageRoom.getCreateDate();
    }
}