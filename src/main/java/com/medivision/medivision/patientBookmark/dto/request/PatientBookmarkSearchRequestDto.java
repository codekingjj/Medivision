package com.medivision.medivision.patientBookmark.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientBookmarkSearchRequestDto {
    private String pid;
    private String pname;
}
