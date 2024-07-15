package com.medivision.medivision.chat.chatUserSearch.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChatUserSearchListResponseDto {
    private boolean isLastPage;
    private List<ChatUserSearchResponseDto> chatUserSearchDtoList;
}