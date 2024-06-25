package com.medivision.pacs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "V_STUDYTAB", schema = "PACSPLUS")
@Entity
public class PacsEntity {

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
