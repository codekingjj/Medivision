package com.medivision.medivision.chat.chatroom.controller;

import com.medivision.medivision.chat.chat.domain.ChatService;
import com.medivision.medivision.chat.chatroom.domain.ChatroomService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatroomController {
    private final ChatroomService chatroomService;
    private final ChatService chatService;

    @GetMapping("/{roomId}")
    public ModelAndView chatroomList(@PathVariable int roomId) {
        ModelAndView mav = new ModelAndView("chat/chatroom");

        mav.addObject("roomId", roomId);

        return mav;
    }
}