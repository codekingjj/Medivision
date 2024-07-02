package com.medivision.pacs.entity;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "V_SERIESTAB", schema = "PACSPLUS")
@Entity
public class VSeriesEntity {


    private int studyKey;
    @Id
    private int seriesKey;
    private String seriesDesc;
    private int imageCnt;
    private String path;
    private String fName;


}
