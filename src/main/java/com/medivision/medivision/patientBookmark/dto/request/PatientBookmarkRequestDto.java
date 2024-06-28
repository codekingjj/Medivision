package com.medivision.medivision.patientBookmark.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class PatientBookmarkRequestDto {
    private int userCode;
    private String pid;
    private Timestamp createdDate;
}
