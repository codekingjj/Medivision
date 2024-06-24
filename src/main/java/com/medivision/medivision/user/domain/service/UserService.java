package com.medivision.medivision.user.domain.service;

import com.medivision.medivision.user.dto.request.SignInRequestDto;
import com.medivision.medivision.user.dto.response.SignInResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super SignInResponseDto> signin(SignInRequestDto dto);
}
