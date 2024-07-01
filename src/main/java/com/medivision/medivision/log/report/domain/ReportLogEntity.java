package com.medivision.medivision.log.report.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log_report")
@Entity
public class ReportLogEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logIndex;
    private String userId;
    private String clientIp;
    private String reportType;
    private String comment;
    private String finding;
    private String conclusion;
    private String recommend;
    private int studyKey;
    private Timestamp reportDate;
}
