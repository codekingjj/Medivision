package com.medivision.medivision.chat.chatUserSearch.controller;

import com.medivision.medivision.chat.chatUserSearch.utils.ChatUserSearchPage;
import com.medivision.medivision.chat.chatUserSearch.dto.response.ChatUserSearchListResponseDto;
import com.medivision.medivision.chat.chatUserSearch.dto.request.ChatUserSearchRequestDto;
import com.medivision.medivision.chat.chatUserSearch.dto.response.ChatUserSearchResponseDto;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.AdminRepository;
import com.medivision.medivision.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat/userSearch")
@RequiredArgsConstructor
public class ChatUserSearchController {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<ChatUserSearchListResponseDto> chatroomList(@RequestBody ChatUserSearchRequestDto chatUserSearchDto, @AuthenticationPrincipal String userCode) {
        Pageable pageable = PageRequest.of(chatUserSearchDto.getPageNumber(), ChatUserSearchPage.ITEM_SIZE);

        Page<UserEntity> users = userRepository.findAllByUserIdContainingAndUserCodeNot(
            pageable, chatUserSearchDto.getSearchQuery(), Integer.parseInt(userCode));

        List<ChatUserSearchResponseDto> chatUserSearchResponseDtoList = new ArrayList<>();

        for (UserEntity user : users) {
            ChatUserSearchResponseDto chatUserSearchResponseDto = new ChatUserSearchResponseDto(user);

            AdminEntity admin = adminRepository.findByUserCode(user.getUserCode());

            chatUserSearchResponseDto.setUserName(admin.getUserName());
            chatUserSearchResponseDtoList.add(chatUserSearchResponseDto);
        }

        ChatUserSearchListResponseDto chatUserSearchListDto = new ChatUserSearchListResponseDto();
        chatUserSearchListDto.setChatUserSearchDtoList(chatUserSearchResponseDtoList);
        chatUserSearchListDto.setLastPage(users.isLast());

        return new ResponseEntity<>(chatUserSearchListDto, HttpStatus.OK);
    }
}