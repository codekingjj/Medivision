package com.medivision.medivision.chat.controller;

import com.medivision.medivision.chat.dto.ChatRequestDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @MessageMapping("/chat/sendMessage")
    @SendTo("/topic/messages")
    public String sendMessage(@RequestBody ChatRequestDto chatRequestDto) throws Exception {
        System.out.println("room id:  " + chatRequestDto.getRoomId());
        System.out.println("sender user code:  " + chatRequestDto.getSenderUserCode());
        System.out.println("message:  " + chatRequestDto.getMessage());

        return chatRequestDto.getMessage();
    }
}