package com.medivision.medivision.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="report")
public class ReportRequestDto {
    private int studyKey;
    private int writer;
    private String comment;
    private String finding;
    private String conclusion;
    private String recommend;
    private String typeDecode;

}
