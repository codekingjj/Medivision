package com.medivision.medivision.patientBookmark.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PatientBookmarkId implements Serializable {
    private int userCode;
    private int pid;
}
