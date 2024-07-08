package com.medivision.medivision.chat.chatroom.utils;

import com.medivision.medivision.user.domain.entity.AdminEntity;

import java.util.List;

public class ChatroomUtils {
    public static String createName(List<AdminEntity> members) {
        StringBuilder name = new StringBuilder();

        for (int i = 0; i < members.size(); i++) {
            AdminEntity member = members.get(i);

            name.append(member.getUserName());

            if (i < members.size() - 1) {
                name.append(", ");
            }
        }

        return name.toString();
    }
}