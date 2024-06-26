package com.medivision.medivision.chat.roomMember.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChatRoomMemberId implements Serializable {
    private int roomId;
    private int userCode;
}
