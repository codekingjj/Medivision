package com.medivision.medivision.chat.chatroomAndMember.domain;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChatroomAndMemberId implements Serializable {
    private int roomId;
    private int userCode;
}