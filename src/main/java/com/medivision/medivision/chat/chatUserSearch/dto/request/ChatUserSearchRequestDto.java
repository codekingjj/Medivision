package com.medivision.medivision.chat.chatUserSearch.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatUserSearchRequestDto {
    private String searchQuery;
    private int pageNumber;
}
