package com.medivision.medivision.chat.room.controller;

import com.medivision.medivision.chat.room.domain.ChatRoom;
import com.medivision.medivision.chat.room.dto.ChatRoomRequestDto;
import com.medivision.medivision.chat.room.domain.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chatRoom")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private int userCode = 2;

    @GetMapping("/all")
    public ModelAndView findChatRoomAllByUserCode() {
        ModelAndView mav = new ModelAndView("chat/roomList");
        List<ChatRoom> rooms = chatRoomService.findAllByUserCode(userCode);

        mav.addObject("rooms", rooms);

        return mav;
    }

    @GetMapping("/{roomId}")
    public ModelAndView findChatRoomByRoomId(@PathVariable int roomId) {
        ModelAndView mav = new ModelAndView("chat/room");
        ChatRoom room = chatRoomService.findByRoomId(roomId);

        mav.addObject("room", room);

        return mav;
    }

    @PostMapping("/create")
    public ChatRoom createChatRoom(@RequestBody ChatRoomRequestDto chatRoomDto) {
        ChatRoom room = chatRoomService.create(chatRoomDto);

        return room;
    }

    @DeleteMapping("/delete")
    public ModelAndView deleteChatRoom(@RequestBody ChatRoomRequestDto chatRoomDto) {
        chatRoomService.delete(chatRoomDto);

        ModelAndView mav = new ModelAndView("chat/roomList");

        return mav;
    }

    @PostMapping("/changeChatRoomName")
    public ChatRoom changeChatRoomName(@RequestBody ChatRoomRequestDto chatRoomDto) {
        ChatRoom room = chatRoomService.update(chatRoomDto);

        return room;
    }
}