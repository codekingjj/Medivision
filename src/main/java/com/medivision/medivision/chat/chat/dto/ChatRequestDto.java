package com.medivision.medivision.chat.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ChatRequestDto {
    private int chatId;
    private int roomId;
    private int senderUserCode;
    private boolean isSystemMessage;
    private String message;
    private Timestamp createDate;
}
