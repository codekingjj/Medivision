package com.medivision.message.roomMember.domain;

import java.io.Serializable;

public class MessageRoomMemberId implements Serializable {
    private int roomId;
    private int userCode;

    public MessageRoomMemberId(int roomId, int userCode) {
        this.roomId = roomId;
        this.userCode = userCode;
    }
}
