package com.medivision.pacs.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "V_SERIESTAB", schema = "PACSPLUS")
@Entity
public class VSeriesEntity {
    @Id
    private int StudyKey;
    @Id
    private int SeriesKey;
    private String seriesdecs;
    private int imagecnt;
    private String path;
    private String fname;
}
