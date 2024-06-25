package com.medivision.pacs.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "V_STUDYTAB", schema = "PACSPLUS")
@Entity
public class VStudyEntity {

    @Id
    private int StudyKey;
    private String pid;
    private String pname;
    private String modality;
    private String studydesc;
    private String studydate;
    private int reportstatus;
    private int seriescnt;
    private int imagecnt;
    private int examstatus;
}
