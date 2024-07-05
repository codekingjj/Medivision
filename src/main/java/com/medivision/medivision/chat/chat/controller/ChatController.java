package com.medivision.medivision.chat.chat.controller;

import com.medivision.medivision.chat.chat.domain.Chat;
import com.medivision.medivision.chat.chat.domain.ChatService;
import com.medivision.medivision.chat.chat.dto.ChatListResponseDto;
import com.medivision.medivision.chat.chat.dto.ChatResponseDto;
import com.medivision.medivision.chat.chat.dto.PageRequestDto;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.AdminRepository;
import com.medivision.medivision.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final AdminRepository adminRepository;

    @MessageMapping("/chat")
    @SendTo("/chatroom/{roomId}")
    public ChatResponseDto index(Chat chat) {
        ChatResponseDto chatResponseDto = new ChatResponseDto(chat);

        AdminEntity admin = adminRepository.findByUserCode(chat.getSenderUserCode());

        chatResponseDto.setSenderUserName(admin.getUserName());

        chatService.save(chat);

        return chatResponseDto; // return value goes to @SendTo() URL
    }

    @PostMapping("/chat/{roomId}")
    public ResponseEntity<ChatListResponseDto> chat(@PathVariable int roomId, @RequestBody PageRequestDto pageDto) {

        Page<Chat> chats = chatService.findByRoomIdOrderByCreateDateDesc(roomId, pageDto.getPageNumber());
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        for (Chat chat : chats) {
            AdminEntity admin = adminRepository.findByUserCode(chat.getSenderUserCode());

            ChatResponseDto chatResponseDto = new ChatResponseDto(chat);
            chatResponseDto.setSenderUserName(admin.getUserName());

            chatResponseDtoList.add(chatResponseDto);
        }

        ChatListResponseDto chatListResponseDto = new ChatListResponseDto();
        chatListResponseDto.setChatDtoList(chatResponseDtoList);
        chatListResponseDto.setLastPage(chats.isLast());

        return new ResponseEntity<>(chatListResponseDto, HttpStatus.OK);
    }

    @GetMapping("/chat/userCode")
    public ResponseEntity<String> getUserCode(@AuthenticationPrincipal String userCode) {
       return new ResponseEntity<>(userCode, HttpStatus.OK);
    }
}