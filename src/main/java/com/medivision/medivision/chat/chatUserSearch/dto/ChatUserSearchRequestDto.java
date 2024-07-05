package com.medivision.medivision.chat.chatUserSearch.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatUserSearchRequestDto {
    private String searchQuery;
    private int pageNumber;
}
