package com.medivision.message.room.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageRoomService {
    private final MessageRoomRepository messageRoomRepository;

    public List<MessageRoom> findAllByUserCode(int userCode) {
        return messageRoomRepository.findAll();
        //return messageRoomRepository.findAllByUserCode(userCode);
    }

    public MessageRoom create(MessageRoomRequestDto msgRoomDto) {
        MessageRoom msgRoom = new MessageRoom();
        msgRoom.update(msgRoomDto);

        return messageRoomRepository.save(msgRoom);
    }

    public void delete(MessageRoomRequestDto msgRoomDto) {
        MessageRoom msgRoom = new MessageRoom();
        msgRoom.update(msgRoomDto);

        messageRoomRepository.deleteById(msgRoom.getRoomId());
    }

    public MessageRoom update(MessageRoomRequestDto msgRoomDto) {
        MessageRoom msgRoom = new MessageRoom();
        msgRoom.update(msgRoomDto);

        return messageRoomRepository.save(msgRoom);
    }
}