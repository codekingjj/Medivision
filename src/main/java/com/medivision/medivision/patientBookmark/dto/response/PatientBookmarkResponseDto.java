package com.medivision.medivision.patientBookmark.dto.response;

import com.medivision.medivision.patientBookmark.domain.PatientBookmark;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class PatientBookmarkResponseDto {
    private String pid;
    private String pname;
}
