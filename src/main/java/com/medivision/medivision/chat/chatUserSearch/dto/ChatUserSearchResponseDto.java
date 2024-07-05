package com.medivision.medivision.chat.chatUserSearch.dto;

import com.medivision.medivision.user.domain.entity.AdminEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatUserSearchResponseDto {
    private int userCode;
    private String userName;

    public ChatUserSearchResponseDto(AdminEntity user) {
        this.userCode = user.getUserCode();
        this.userName = user.getUserName();
    }
}