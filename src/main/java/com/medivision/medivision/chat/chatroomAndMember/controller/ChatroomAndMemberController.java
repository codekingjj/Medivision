package com.medivision.medivision.chat.chatroomAndMember.controller;

import com.medivision.medivision.chat.chatroomAndMember.domain.ChatroomAndMember;
import com.medivision.medivision.chat.chatroomAndMember.dto.ChatroomAndMemberResponseDto;
import com.medivision.medivision.chat.chatroomAndMember.service.ChatroomAndMemberService;
import com.medivision.medivision.user.domain.entity.AdminEntity;

import com.medivision.medivision.user.domain.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chatroomAndMember")
@RequiredArgsConstructor
public class ChatroomAndMemberController {
    private final ChatroomAndMemberService chatroomAndMemberService;
    private final AdminRepository adminRepository;

    @GetMapping("")
    public ModelAndView chatroomListPage() {
        ModelAndView mav = new ModelAndView("chat/chatroomList");

        return mav;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChatroomAndMemberResponseDto>> getChatroomList(@AuthenticationPrincipal String userCode) {
        List<ChatroomAndMember> chatroomAndMembers = chatroomAndMemberService.findAllByUserCode(Integer.parseInt(userCode));
        List<ChatroomAndMemberResponseDto> chatroomAndMemberResponseDtoList = new ArrayList<>();

        for(ChatroomAndMember chatroomAndMember : chatroomAndMembers) {
            ChatroomAndMemberResponseDto chatroomAndMemberResponseDto = new ChatroomAndMemberResponseDto(chatroomAndMember);

            List<AdminEntity> users = adminRepository.findAllByRoomId(chatroomAndMemberResponseDto.getRoomId());

            chatroomAndMemberResponseDto.setMembers(users);
            chatroomAndMemberResponseDtoList.add(chatroomAndMemberResponseDto);
        }

        return new ResponseEntity<>(chatroomAndMemberResponseDtoList, HttpStatus.OK);
    }
}