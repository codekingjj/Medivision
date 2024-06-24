package com.medivision.medivision.user.domain.service;

import com.medivision.medivision.user.dto.request.SignUpRequestDto;
import com.medivision.medivision.user.dto.response.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
}
