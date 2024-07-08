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
}