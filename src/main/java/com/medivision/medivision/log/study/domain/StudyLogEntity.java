package com.medivision.medivision.log.study.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "log_study")
@Entity
public class StudyLogEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logIndex;
    private String userId;
    private String clientIp;
    private LocalDateTime openDate;
    private int studyKey;
}
