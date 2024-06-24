package com.medivision.medivision.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "admin")
@Table(name="admin")
public class AdminEntity {

    @Id @Column(name = "user_code")
    private int userCode;
    @Column(name = "user_birthday")
    private String userBirthday;
    @Column(name = "user_juminSC")
    private String userJuminSC;
    @Column(name = "user_phonenumber")
    private String userPhonenumer;
    @Column(name = "user_telecom")
    private String userTelecom;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_licensenum")
    private String userLicensenum;
    @Column(name = "signup")
    private Boolean signup;
}
