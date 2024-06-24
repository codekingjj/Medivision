package com.medivision.medivision.user.domain;

import com.medivision.medivision.user.dto.SignUpRequestDto;
import com.medivision.medivision.user.dto.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
}
