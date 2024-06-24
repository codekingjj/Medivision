package com.medivision.message.roomMember.controller;

import com.websocket.chat.message.room.domain.MessageRoom;
import com.websocket.chat.message.roomMember.domain.MessageRoomMember;
import com.websocket.chat.message.roomMember.domain.MessageRoomMemberRequestDto;
import com.websocket.chat.message.roomMember.domain.MessageRoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roomMember")
public class MessageRoomMemberController {
    private final MessageRoomMemberService messageRoomMemberService;

    @PostMapping("/add")
    public ModelAndView findRoomAllByUserCode(@RequestBody MessageRoomMember messageRoomMember) {

        ModelAndView mav = new ModelAndView("message/roomList");

        mav.addObject("messageRoomMember", messageRoomMember);

        return mav;
    }

    @DeleteMapping("/delete")
    public ModelAndView deleteByRoomIdAndUserCode(@RequestBody MessageRoomMemberRequestDto messageRoomMemberDto) {
        messageRoomMemberService.deleteByRoomIdAndUserCode(messageRoomMemberDto);

        System.out.println(messageRoomMemberDto.getRoomId());
        System.out.println(messageRoomMemberDto.getUserCode());

        ModelAndView mav = new ModelAndView("message/roomList");

        return mav;
    }
}
