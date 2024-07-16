package com.medivision.medivision.patientBookmark.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientBookmarkResponseDto {
    private String pid;
    private String pname;
    private String psex;
    private String pbirthdate;
}
