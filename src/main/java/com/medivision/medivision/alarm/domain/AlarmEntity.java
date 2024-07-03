package com.medivision.medivision.alarm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import oracle.sql.TIMESTAMP;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alarm")
@Entity
public class AlarmEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alarmIndex;
    private int userCode;
    private String content;
    private boolean isCheck;
    @CreatedDate
    private TIMESTAMP regDate;
}
