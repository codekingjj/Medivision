package com.medivision.medivision.chat.chatUserSearch.dto;

import com.medivision.medivision.user.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatUserSearchResponseDto {
    private int userCode;
    private String userName;
    private String userId;

    public ChatUserSearchResponseDto(UserEntity user) {
        this.userCode = user.getUserCode();
        this.userId = user.getUserId();
    }
}