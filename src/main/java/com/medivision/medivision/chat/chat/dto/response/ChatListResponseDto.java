package com.medivision.medivision.chat.chat.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChatListResponseDto {
    private boolean isLastPage;
    private List<ChatResponseDto> chatDtoList;
}
