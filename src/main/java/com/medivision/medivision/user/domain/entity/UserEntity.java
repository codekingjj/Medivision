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
@Table(name = "user", schema = "medivision")
@Entity
public class UserEntity {

    @Id //@Column(name = "user_code")
    private int userCode;
    //@Column(name = "user_id")
    private String userId;
    //@Column(name = "user_password")
    private String userPassword;

}
