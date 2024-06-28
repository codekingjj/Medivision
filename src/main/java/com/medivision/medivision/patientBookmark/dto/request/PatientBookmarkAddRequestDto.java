package com.medivision.medivision.patientBookmark.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientBookmarkAddRequestDto {
    private String[] pids;
}