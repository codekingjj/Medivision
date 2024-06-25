package com.medivision.medivision.report.dto;

import com.medivision.medivision.report.domain.service.ReportEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDto {
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

    private String writerCode;

    public ReportResponseDto(ReportEntity reportEntity, String writerCode) {
        this.reportIndex = reportEntity.getReportIndex();
        this.studyKey = reportEntity.getStudyKey();
        this.writer = reportEntity.getWriter();
        this.comment = reportEntity.getComment();
        this.finding = reportEntity.getFinding();
        this.conclusion = reportEntity.getConclusion();
        this.recommend = reportEntity.getRecommend();
        this.regDate = reportEntity.getRegDate();
        this.modDate = reportEntity.getModDate();
        this.typeDecode = reportEntity.getTypeDecode();
        this.writerCode = writerCode;
    }

}
