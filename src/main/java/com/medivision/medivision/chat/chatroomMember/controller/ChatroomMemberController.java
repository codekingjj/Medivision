package com.medivision.medivision.chat.chatroomMember.controller;

import com.medivision.medivision.chat.chat.domain.Chat;
import com.medivision.medivision.chat.chatroom.domain.Chatroom;
import com.medivision.medivision.chat.chatroom.domain.ChatroomService;
import com.medivision.medivision.chat.chatroom.dto.ChatroomRequestDto;
import com.medivision.medivision.chat.chatroomAndMember.domain.ChatroomAndMember;
import com.medivision.medivision.chat.chatroomAndMember.dto.ChatroomAndMemberRequestDto;
import com.medivision.medivision.chat.chatroomAndMember.dto.ChatroomAndMemberResponseDto;
import com.medivision.medivision.chat.chatroomAndMember.service.ChatroomAndMemberService;
import com.medivision.medivision.chat.chatroomMember.domain.ChatroomMember;
import com.medivision.medivision.chat.chatroomMember.domain.ChatroomMemberService;
import com.medivision.medivision.chat.chatroomMember.dto.ChatroomMemberRequestDto;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chatroomMember")
@RequiredArgsConstructor
public class ChatroomMemberController {
    private final ChatroomMemberService chatroomMemberService;

    @PostMapping("")
    public ResponseEntity<List<ChatroomMember>> findByUserCode(int userCode) {
        List<ChatroomMember> chatroomMembers = chatroomMemberService.findByUserCode(userCode);

        return new ResponseEntity<>(chatroomMembers, HttpStatus.OK);
    }

    @PostMapping("/saveLastVisitedDate")
    public ResponseEntity<HttpStatus> saveLastVisitedDate(@RequestBody ChatroomMemberRequestDto chatroomMemberRequestDto) {
        ChatroomMember chatroomMember = new ChatroomMember(chatroomMemberRequestDto);

        chatroomMemberService.save(chatroomMember);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}