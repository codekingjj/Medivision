package com.medivision.medivision.user.domain.service;

import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.dto.request.SignInRequestDto;
import com.medivision.medivision.user.dto.request.SignUpRequestDto;
import com.medivision.medivision.user.dto.response.SignUpResponseDto;
import com.medivision.medivision.user.dto.response.UserListReponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;


public interface AdminService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    Page<AdminEntity> userLIst(int pageNum, int pageSize);

    boolean adminSignIn(SignInRequestDto requestDto);
    long getTotalCount();
}
