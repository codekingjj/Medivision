package com.medivision.medivision.chat.chatroomMember.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChatroomMemberId implements Serializable {
    private int roomId;
    private int userCode;

    @Override
    public int hashCode() {
        return Objects.hash(roomId, userCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatroomMemberId chatroomMemberId = (ChatroomMemberId) o;
        return roomId == chatroomMemberId.roomId && userCode == chatroomMemberId.userCode;
    }
}