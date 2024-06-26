package com.medivision.medivision.chat.room.dto;

import com.medivision.medivision.chat.room.domain.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomResponseDto {
    private  int roomId;
    private  String name;
    private  Timestamp createDate;

    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.roomId = chatRoom.getRoomId();
        this.name = chatRoom.getName();
        this.createDate = chatRoom.getCreateDate();
    }
}