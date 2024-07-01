package com.medivision.pacs.entity;

import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Getter
@Table(name = "V_IMAGETAB", schema = "PACSPLUS")
@Entity
public class VImageEntity {

    @Id
    private int studyKey;
    private int seriesKey;
    private int imageKey;
    private String path;
    private String fname;
}
