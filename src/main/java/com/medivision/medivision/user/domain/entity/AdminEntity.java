package com.medivision.medivision.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "admin")
@Table(name="admin")
public class AdminEntity {

    @Id
    private int userCode;
    private String userBirthday;
    private String userJuminSC;
    private String userPhonenumber;
    private String userTelecom;
    private String userName;
    private String userLicensenum;
    private Boolean signup;
}
