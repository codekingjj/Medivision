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
    private long studykey;

    private String studydate;
    private String studydesc;
    private String modality;
    private String bodypart;
    private String pid;
    private String pname;
    private String psex;
    private long seriescnt;
    private long imagecnt;
    private long examstatus;
    private String pbirthdatetime;


}
