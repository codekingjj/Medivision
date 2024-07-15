package com.medivision.medivision.chat.chatroomAndMember.controller;

import com.medivision.medivision.chat.chat.domain.entity.Chat;
import com.medivision.medivision.chat.chat.domain.service.ChatService;
import com.medivision.medivision.chat.chatroom.domain.entity.Chatroom;
import com.medivision.medivision.chat.chatroom.domain.service.ChatroomService;
import com.medivision.medivision.chat.chatroom.utils.ChatroomUtils;
import com.medivision.medivision.chat.chatroomAndMember.domain.entity.ChatroomAndMember;
import com.medivision.medivision.chat.chatroomAndMember.dto.request.ChatroomAndMemberRequestDto;
import com.medivision.medivision.chat.chatroomAndMember.dto.response.ChatroomAndMemberResponseDto;
import com.medivision.medivision.chat.chatroomAndMember.domain.service.ChatroomAndMemberService;
import com.medivision.medivision.chat.chatroomMember.domain.entity.ChatroomMember;
import com.medivision.medivision.chat.chatroomMember.domain.service.ChatroomMemberService;
import com.medivision.medivision.chat.chatroomMember.dto.request.ChatroomMemberRequestDto;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chatroomAndMember")
@RequiredArgsConstructor
public class ChatroomAndMemberController {
    private final ChatService chatService;
    private final ChatroomService chatroomService;
    private final ChatroomMemberService chatroomMemberService;
    private final ChatroomAndMemberService chatroomAndMemberService;
    private final AdminRepository adminRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ChatroomAndMemberResponseDto>> getChatroomList(@AuthenticationPrincipal String userCodeString) {
        int userCode = Integer.parseInt(userCodeString);

        List<ChatroomAndMember> chatroomAndMembers = chatroomAndMemberService.findAllByUserCode(userCode);
        List<ChatroomAndMemberResponseDto> chatroomAndMemberResponseDtoList = new ArrayList<>();

        for (ChatroomAndMember chatroomAndMember : chatroomAndMembers) {
            ChatroomAndMemberResponseDto chatroomAndMemberResponseDto = new ChatroomAndMemberResponseDto(chatroomAndMember);
            int roomId = chatroomAndMemberResponseDto.getRoomId();

            List<AdminEntity> users = adminRepository.findAllByRoomId(roomId);
            String latestMessage = "";
            Timestamp latestMessageSendDate = null;

            if (chatService.existsByRoomId(roomId)) {
                Chat latestChat = chatService.findLatestChatByRoomId(roomId);
                latestMessage = latestChat.getMessage();
                latestMessageSendDate = latestChat.getCreateDate();
            }

            int numOfUnreadMessages = chatroomMemberService.countUnreadChatByRoomIdAndUserCode(roomId, userCode);

            chatroomAndMemberResponseDto.setLatestMessage(latestMessage);
            chatroomAndMemberResponseDto.setLatestMessageSendDate(latestMessageSendDate);
            chatroomAndMemberResponseDto.setMembers(users);
            chatroomAndMemberResponseDto.setNumOfUnreadMessages(numOfUnreadMessages);

            chatroomAndMemberResponseDtoList.add(chatroomAndMemberResponseDto);
        }

        return new ResponseEntity<>(chatroomAndMemberResponseDtoList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody ChatroomAndMemberRequestDto chatroomAndMemberRequestDto) {
        for (AdminEntity member : chatroomAndMemberRequestDto.getMembers()) {
            AdminEntity user = adminRepository.findByUserCode(member.getUserCode());
            member.setUserName(user.getUserName());
        }

        String chatroomName = ChatroomUtils.createName(chatroomAndMemberRequestDto.getMembers());

        Chatroom chatroom = new Chatroom(chatroomName);
        Chatroom savedChatroom = chatroomService.save(chatroom);

        for (AdminEntity member : chatroomAndMemberRequestDto.getMembers()) {
            ChatroomMember chatroomMember = new ChatroomMember(savedChatroom.getRoomId(), member.getUserCode());
            chatroomMemberService.save(chatroomMember);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestBody ChatroomMemberRequestDto chatroomMemberRequestDto) {
        ChatroomMember chatroomMember = new ChatroomMember(chatroomMemberRequestDto);

        int roomId = chatroomMemberRequestDto.getRoomId();
        chatroomMemberService.delete(chatroomMember);

        Chatroom currentChatroom = chatroomService.findByRoomId(roomId);
        List<AdminEntity> members = adminRepository.findAllByRoomId(roomId);

        String newChatroomName = ChatroomUtils.createName(members);
        Chatroom chatroomWithNewName = new Chatroom(currentChatroom.getRoomId(), newChatroomName);

        chatroomService.save(chatroomWithNewName);

        boolean hasMember = chatroomMemberService.existByChatroomId(roomId);

        if (!hasMember)
            chatroomService.deleteByRoomId(roomId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}