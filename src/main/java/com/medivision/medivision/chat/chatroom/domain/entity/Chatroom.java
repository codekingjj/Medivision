package com.medivision.medivision.chat.chatroom.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_rooms", schema = "chat")
@Entity
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private String name;

    @CreationTimestamp
    private Timestamp createDate;

    public Chatroom(int roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public Chatroom(String name) {
        this.name = name;
    }
}
