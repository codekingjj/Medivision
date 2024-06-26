package com.medivision.medivision.chat.roomMember.controller;

import com.medivision.medivision.chat.roomMember.domain.ChatRoomMember;
import com.medivision.medivision.chat.roomMember.dto.ChatRoomMemberRequestDto;
import com.medivision.medivision.chat.roomMember.domain.ChatRoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chatRoomMember")
public class ChatRoomMemberController {
    private final ChatRoomMemberService chatRoomMemberService;

//    @PostMapping("/user")
//    public ModelAndView findChatRoomAllByUserCode(@RequestBody ChatRoomMember chatRoomMember) {
//
//        ModelAndView mav = new ModelAndView("message/roomList");
//
//        mav.addObject("messageRoomMember", chatRoomMember);
//
//        return mav;
//    }

    @PostMapping("/addOne")
    public ChatRoomMember addMemberByUserCode(@RequestBody ChatRoomMemberRequestDto chatRoomMemberDto) {
        return chatRoomMemberService.addMemberByUserCode(chatRoomMemberDto);
    }

    @PostMapping("/addMany")
    public List<ChatRoomMember> addMembersByUserCode(@RequestBody Map<String, List<ChatRoomMemberRequestDto>> chatRoomMemberDto) {
        return chatRoomMemberService.addMembersByUserCode(chatRoomMemberDto.get("chat_room_members"));
    }

    @DeleteMapping("/delete")
    public ModelAndView deleteByRoomIdAndUserCode(@RequestBody ChatRoomMemberRequestDto chatRoomMemberDto) {
        chatRoomMemberService.deleteByRoomIdAndUserCode(chatRoomMemberDto);

        ModelAndView mav = new ModelAndView("chat/roomList");

        return mav;
    }
}
