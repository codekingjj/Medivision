package com.medivision.pacs.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "PATIENTTAB", schema = "PACSPLUS")
@Entity
public class PatientEntity {
    @Id
    private String pid;
    private String pName;
}
