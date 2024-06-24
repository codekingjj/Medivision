package com.medivision.message.room.controller;

import com.websocket.chat.message.domain.Message;
import com.websocket.chat.message.room.domain.MessageRoom;
import com.websocket.chat.message.room.domain.MessageRoomRequestDto;
import com.websocket.chat.message.room.domain.MessageRoomService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/room")
public class MessageRoomController {
    private final MessageRoomService messageRoomService;
    private int userCode = 1;

    @GetMapping("/all")
    public ModelAndView findRoomAllByUserCode() {
        ModelAndView mav = new ModelAndView("message/roomList");
        List<MessageRoom> rooms = messageRoomService.findAllByUserCode(userCode);

        mav.addObject("rooms", rooms);

        return mav;
    }

//    @GetMapping("/{roomId}")
//    public ModelAndView getMessagesByRoomId(@PathVariable("roomId") int roomId) {
//        ModelAndView mav = new ModelAndView("message/room");
//        List<Message> messages = messageService.findAllByRoomId(userCode);
//
//        mav.addObject("messsages", messages);
//
//        return mav;
//    }

    @PostMapping("/create")
    public MessageRoom createMessageRoom(@RequestBody MessageRoomRequestDto msgRoomDto) {
        MessageRoom room = messageRoomService.create(msgRoomDto);

        return room;
    }

    @DeleteMapping("/delete")
    public ModelAndView deleteMessageRoom(@RequestBody MessageRoomRequestDto msgRoomDto) {
        ModelAndView mav = new ModelAndView("message/roomList");

        messageRoomService.delete(msgRoomDto);

        return mav;
    }

    @PostMapping("/changeRoomName")
    public MessageRoom changeRoomName(@RequestBody MessageRoomRequestDto msgRoomDto) {
        MessageRoom room = messageRoomService.update(msgRoomDto);

        return room;
    }
}