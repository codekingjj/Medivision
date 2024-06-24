package com.medivision.medivision.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    private String juminSC;
    private int userCode;
}
