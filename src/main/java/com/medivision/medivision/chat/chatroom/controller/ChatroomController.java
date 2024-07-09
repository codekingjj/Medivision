package com.medivision.medivision.chat.chatroom.controller;

import com.medivision.medivision.chat.chatroom.domain.ChatroomService;

import com.medivision.medivision.chat.chatroomMember.domain.ChatroomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatroomController {
    @GetMapping("")
    public ModelAndView chatroomListPage() {
        ModelAndView mav = new ModelAndView("chat/chatroomList");

        return mav;
    }

    @GetMapping("/{roomId}")
    public ModelAndView chatroomList(@PathVariable int roomId) {
        ModelAndView mav = new ModelAndView("chat/chatroom");

        mav.addObject("roomId", roomId);

        return mav;
    }
}