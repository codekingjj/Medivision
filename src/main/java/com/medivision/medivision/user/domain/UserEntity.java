package com.medivision.medivision.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity(name="user")
public class UserEntity {

    @Id
    private int userCode;
    private String userId;
    private String userPassword;

}
