package com.medivision.medivision.chat.chat.dto;

import com.medivision.medivision.chat.chat.domain.Chat;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Setter
@Getter
public class ChatResponseDto {
    private int chatId;
    private int roomId;
    private int senderUserCode;
    private String senderUserName;

    private String message;
    private boolean isSystemMessage;
    private Timestamp createDate;

    public ChatResponseDto(Chat chat) {
        this.chatId = chat.getChatId();
        this.roomId = chat.getRoomId();
        this.senderUserCode = chat.getSenderUserCode();
        this.message = chat.getMessage();
        this.createDate = chat.getCreateDate();
    }
}