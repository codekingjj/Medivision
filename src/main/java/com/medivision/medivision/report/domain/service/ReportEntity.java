package com.medivision.medivision.report.domain.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="report")
@Table(name="report")
public class ReportEntity {
    @Id
    private int reportIndex;

    private int studyKey;
    private int writer;

    private String comment;
    private String finding;
    private String conclusion;
    private String recommend;
    private Timestamp regDate;
    private Timestamp modDate;
    private String typeDecode;
}
