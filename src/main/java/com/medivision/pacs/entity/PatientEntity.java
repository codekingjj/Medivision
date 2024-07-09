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
@Table(name = "PATIENTTAB", schema = "PACSPLUS")
@Entity
public class PatientEntity {
    @Id
    private String pid;

    private String pname;
}
