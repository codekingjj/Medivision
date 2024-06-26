package com.medivision.medivision.report.domain.service;

import com.medivision.medivision.report.dto.ReportRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    public ReportEntity(ReportRequestDto reportRequestDto) {
        this.studyKey = reportRequestDto.getStudyKey();
        this.writer = reportRequestDto.getWriter();
        this.comment = reportRequestDto.getComment();
        this.finding = reportRequestDto.getFinding();
        this.conclusion = reportRequestDto.getConclusion();
        this.recommend = reportRequestDto.getRecommend();
        this.typeDecode = reportRequestDto.getTypeDecode();
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = Timestamp.valueOf(dateFormat.format(date));
        System.out.println(timestamp);
        this.regDate = timestamp;

    }
}
