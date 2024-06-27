package com.medivision.medivision.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @MessageMapping("/sendMessage")
    @SendTo("/chat/messages")
    public String sendMessage(String message) throws Exception {
        System.out.println("message:  " + message);
        return message;
    }
}
