package com.medivision.medivision.decode.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="decode")
@Entity(name="decode")
public class DecodeEntity {
    @Id
    private int studyKey;
    private String decode;


}
