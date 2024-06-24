package com.medivision.message.domain;

import com.websocket.chat.message.room.domain.MessageRoom;
import com.websocket.chat.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "roomId")
    private MessageRoom messageRoom;

    @ManyToOne
    @JoinColumn(name = "sender_user_code", referencedColumnName = "code")
    private User user;

    private String message;
    private Timestamp createDate;

    public enum MessageType {
        ENTER,TALK,LEAVE
    }
}
