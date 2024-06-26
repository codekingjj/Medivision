package com.medivision.medivision.chat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "chats")
public class Chat {
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
