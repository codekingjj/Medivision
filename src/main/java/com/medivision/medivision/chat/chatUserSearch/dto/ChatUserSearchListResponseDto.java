package com.medivision.medivision.chat.chatUserSearch.dto;

import com.medivision.medivision.chat.chat.dto.ChatResponseDto;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChatUserSearchListResponseDto {
    private boolean isLastPage;
    private List<ChatUserSearchResponseDto> chatUserSearchDtoList;
}