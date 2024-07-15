package com.medivision.medivision.patientBookmark.domain.entity;

import com.medivision.medivision.patientBookmark.domain.PatientBookmarkId;
import com.medivision.medivision.patientBookmark.dto.request.PatientBookmarkRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdDate;

    public PatientBookmark(PatientBookmarkRequestDto patientBookmarkResponseDto) {
        this.userCode = patientBookmarkResponseDto.getUserCode();
        this.pid = patientBookmarkResponseDto.getPid();
    }
}
