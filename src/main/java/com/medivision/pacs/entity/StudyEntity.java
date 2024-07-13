package com.medivision.pacs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="STUDYTAB",schema="PACSPLUS")
@Entity
public class StudyEntity {
    @Id
    private int studykey;

    private String studydate;
    private String studydesc;
    private String modality;
    private String bodypart;
    private String pid;
    private String pname;
    private String psex;
    private int seriescnt;
    private int imagecnt;
    private int examstatus;
    private String pbirthdatetime;

    private String aimodelname;
    private String aifinding;
    private String aireport;

    public StudyEntity(int studykey){
        this.studykey = studykey;
    }


}
