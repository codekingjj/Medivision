package com.medivision.medivision.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String userPassword;

}
