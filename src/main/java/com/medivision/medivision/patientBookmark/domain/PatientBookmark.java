package com.medivision.medivision.patientBookmark.domain;

import com.medivision.medivision.patientBookmark.dto.request.PatientBookmarkRequestDto;
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
    private String pid;

    private Timestamp createdDate;

    public PatientBookmark(PatientBookmarkRequestDto patientBookmarkResponseDto) {
        this.userCode = patientBookmarkResponseDto.getUserCode();
        this.pid = patientBookmarkResponseDto.getPid();
    }
}
