package com.medivision.medivision.patientBookmark.domain;

import com.medivision.medivision.chat.roomMember.domain.ChatRoomMemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@IdClass(PatientBookmarkId.class)
@Table(name = "patient_bookmarks")
public class PatientBookmark {
    @Id
    @Column(name = "user_code")
    private int userCode;

    @Id
    @Column(name = "pid")
    private int pid;

    private Timestamp createdDate;
}
