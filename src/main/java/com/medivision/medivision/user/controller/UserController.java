package com.medivision.medivision.user.controller;

import com.medivision.medivision.user.domain.service.AdminService;
import com.medivision.medivision.user.dto.SignUpRequestDto;
import com.medivision.medivision.user.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AdminService adminService;

    @PostMapping("/admin")
    public ResponseEntity<? super SignUpResponseDto> signup(@RequestBody SignUpRequestDto requestBody){
        ResponseEntity<? super SignUpResponseDto> response = adminService.signUp(requestBody);
        return  response;
    }

}