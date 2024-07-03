package com.medivision.medivision.chat.controller;

import com.medivision.medivision.alarm.domain.service.AlarmService;
import com.medivision.medivision.chat.dto.ChatRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final AlarmService alarmService;
    @MessageMapping("/chat/sendMessage")
    @SendTo("/topic/messages")
    public String sendMessage(@RequestBody ChatRequestDto chatRequestDto, @AuthenticationPrincipal String userCode) throws Exception {
        System.out.println("room id:  " + chatRequestDto.getRoomId());
        System.out.println("message:  " + chatRequestDto.getMessage());
        chatRequestDto.setSenderUserCode(Integer.parseInt(userCode));

        alarmService.saveChat(chatRequestDto);

        return chatRequestDto.getMessage();
    }
}