package com.medivision.medivision.chat.chatroom.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ChatroomRequestDto {
    private int roomId;
    private String name;
    private Timestamp createDate;
}
