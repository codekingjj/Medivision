package com.medivision.medivision.user.controller;

import com.medivision.medivision.user.domain.service.AdminService;
import com.medivision.medivision.user.domain.service.UserService;
import com.medivision.medivision.user.dto.request.SignInRequestDto;
import com.medivision.medivision.user.dto.request.SignUpRequestDto;
import com.medivision.medivision.user.dto.response.SignInResponseDto;
import com.medivision.medivision.user.dto.response.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AdminService adminService;
    private final UserService userService;
    @PostMapping("/admin")
    public ResponseEntity<? super SignUpResponseDto> signup(@RequestBody SignUpRequestDto requestBody){
        ResponseEntity<? super SignUpResponseDto> response = adminService.signUp(requestBody);
        return  response;
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<? super SignInResponseDto> signin(@RequestBody SignInRequestDto requestBody){
        ResponseEntity<? super  SignInResponseDto> response = userService.signin(requestBody);
        return response;
    }

}
