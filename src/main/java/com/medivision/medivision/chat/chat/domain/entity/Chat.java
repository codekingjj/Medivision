package com.medivision.medivision.chat.chat.domain.entity;

import com.medivision.medivision.chat.chat.dto.request.ChatRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "chats", schema = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;
    private int roomId;
    private int senderUserCode;

    private String message;

    @CreationTimestamp
    private Timestamp createDate;

    public Chat(ChatRequestDto chatRequestDto) {
        this.roomId = chatRequestDto.getRoomId();
        this.message = chatRequestDto.getMessage();
        this.senderUserCode = chatRequestDto.getSenderUserCode();
        this.createDate = chatRequestDto.getCreateDate();
    }
}