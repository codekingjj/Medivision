package com.medivision.medivision.report.domain.service;

import com.medivision.medivision.report.dto.ReportRequestDto;
import com.medivision.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="report")
@Table(name="report")
public class ReportEntity extends Timestamps {
    @Id
    private int reportIndex;

    private int studyKey;
    private int writer;

    private String comment;
    private String finding;
    private String conclusion;
    private String recommend;
    private String typeDecode;


    public ReportEntity(ReportRequestDto reportRequestDto) {
        this.studyKey = reportRequestDto.getStudyKey();
        this.writer = reportRequestDto.getWriter();
        this.comment = reportRequestDto.getComment();
        this.finding = reportRequestDto.getFinding();
        this.conclusion = reportRequestDto.getConclusion();
        this.recommend = reportRequestDto.getRecommend();
        this.typeDecode = reportRequestDto.getTypeDecode();
    }
}
