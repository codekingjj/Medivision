package com.medivision.medivision.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ChatRequestDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;

    @Column(name = "room_id")
    private int roomId;

    @Column(name = "sender_user_code")
    private int senderUserCode;

    private String message;

    @CreationTimestamp
    private Timestamp createDate;

    public enum MessageType {
        ENTER,TALK,LEAVE
    }
}
