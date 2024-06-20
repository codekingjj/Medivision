package com.medivision.medivision.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    private String juminSC;
    @NotBlank
    private int userCode;
}
