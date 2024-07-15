package com.medivision.medivision.chat.chatroomMember.controller;

import com.medivision.medivision.chat.chatroomMember.domain.entity.ChatroomMember;
import com.medivision.medivision.chat.chatroomMember.domain.service.ChatroomMemberService;
import com.medivision.medivision.chat.chatroomMember.dto.request.ChatroomMemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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