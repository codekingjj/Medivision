package com.medivision.medivision.chat.chatUserSearch.controller;

import com.medivision.medivision.chat.chatUserSearch.domain.ChatUserSearchPage;
import com.medivision.medivision.chat.chatUserSearch.dto.ChatUserSearchListResponseDto;
import com.medivision.medivision.chat.chatUserSearch.dto.ChatUserSearchRequestDto;
import com.medivision.medivision.chat.chatUserSearch.dto.ChatUserSearchResponseDto;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat/userSearch")
@RequiredArgsConstructor
public class ChatUserSearchController {
    private final AdminRepository adminRepository;

    @GetMapping("")
    public ModelAndView userSearch() {
        ModelAndView mav = new ModelAndView("/chat/chatUserSearch");

        return mav;
    }

    @PostMapping("")
    public ResponseEntity<ChatUserSearchListResponseDto> chatroomList(@RequestBody ChatUserSearchRequestDto chatUserSearchDto, @AuthenticationPrincipal String userCode) {
        Pageable pageable = PageRequest.of(chatUserSearchDto.getPageNumber(), ChatUserSearchPage.ITEM_SIZE);

        Page<AdminEntity> users = adminRepository.findAllByUserNameContainingAndUserCodeNot(
                pageable, chatUserSearchDto.getSearchQuery(), Integer.parseInt(userCode));

        List<ChatUserSearchResponseDto> chatUserSearchResponseDtoList = new ArrayList<>();

        for (AdminEntity user : users) {
            ChatUserSearchResponseDto chatUserSearchResponseDto = new ChatUserSearchResponseDto(user);
            chatUserSearchResponseDtoList.add(chatUserSearchResponseDto);
        }

        ChatUserSearchListResponseDto chatUserSearchListDto = new ChatUserSearchListResponseDto();
        chatUserSearchListDto.setChatUserSearchDtoList(chatUserSearchResponseDtoList);
        chatUserSearchListDto.setLastPage(users.isLast());

        return new ResponseEntity<>(chatUserSearchListDto, HttpStatus.OK);
    }
}