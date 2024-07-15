package com.medivision.medivision.chat.chat.controller;

import com.medivision.medivision.chat.chat.domain.entity.Chat;
import com.medivision.medivision.chat.chat.domain.service.ChatService;
import com.medivision.medivision.chat.chat.dto.response.ChatListResponseDto;
import com.medivision.medivision.chat.chat.dto.request.ChatRequestDto;
import com.medivision.medivision.chat.chat.dto.response.ChatResponseDto;
import com.medivision.medivision.chat.chat.dto.request.PageRequestDto;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.AdminRepository;
import com.medivision.medivision.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final UserRepository userRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    @SendTo("/topic/chatroom")
    public void emitChatMessageToChatroom(ChatRequestDto chatRequestDto) {
        System.out.println("controller received msg: " + chatRequestDto.getMessage());

        Chat chat = new Chat(chatRequestDto);
        ChatResponseDto chatResponseDto = new ChatResponseDto(chat);

        UserEntity user = userRepository.findByUserCode(chat.getSenderUserCode());
        AdminEntity admin = adminRepository.findByUserCode(user.getUserCode());

        chatResponseDto.setSenderUserId(user.getUserId());
        chatResponseDto.setSenderUserName(admin.getUserName());

        if (chatRequestDto.isSystemMessage()) {
            final String LEAVE_MESSAGE = admin.getUserName() + "님이 방을 나갔습니다";
            chatResponseDto.setSystemMessage(true);
            chatRequestDto.setMessage(LEAVE_MESSAGE);
            chatResponseDto.setMessage(LEAVE_MESSAGE);
        }

        Chat chatWithLeaveMessage = new Chat(chatRequestDto);

        chatService.save(chatWithLeaveMessage);

        final String MSG_DEST_URL = "/topic/chatroom/" + chatResponseDto.getRoomId();

        messagingTemplate.convertAndSend(MSG_DEST_URL, chatResponseDto);
    }

    @PostMapping("/chat/{roomId}")
    public ResponseEntity<ChatListResponseDto> chat(@PathVariable int roomId, @RequestBody PageRequestDto pageDto) {
        Page<Chat> chats = chatService.findByRoomIdOrderByCreateDateDesc(roomId, pageDto.getPageNumber());
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        for (Chat chat : chats) {
            AdminEntity admin = adminRepository.findByUserCode(chat.getSenderUserCode());
            UserEntity user = userRepository.findByUserCode(admin.getUserCode());

            ChatResponseDto chatResponseDto = new ChatResponseDto(chat);
            chatResponseDto.setSenderUserName(admin.getUserName());
            chatResponseDto.setSenderUserId(user.getUserId());

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