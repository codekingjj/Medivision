package com.medivision.medivision.chat.room.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomRequestDto {
    @Id
    private int roomId;
    private String name;
    private Timestamp createDate;
}