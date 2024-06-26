package com.medivision.medivision.user.controller;

import com.medivision.medivision.user.domain.service.AdminService;
import com.medivision.medivision.user.domain.service.UserService;
import com.medivision.medivision.user.dto.request.SignInRequestDto;
import com.medivision.medivision.user.dto.request.SignUpRequestDto;
import com.medivision.medivision.user.dto.response.SignInResponseDto;
import com.medivision.medivision.user.dto.response.SignUpResponseDto;
import com.medivision.medivision.user.dto.response.UserListReponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/auth/sign-in")
    public String signinPage(@AuthenticationPrincipal String code){
        System.out.println("code: "+ code);
        return "user/login";
    }


    @PostMapping("/auth/sign-in")
    public ResponseEntity<? super SignInResponseDto> signin(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword){
        SignInRequestDto requestBody = new SignInRequestDto();
        requestBody.setUserId(userId);
        requestBody.setUserPassword(userPassword);
        ResponseEntity<? super  SignInResponseDto> response = userService.signin(requestBody);
        return response;
    }

    @GetMapping("/admin")
    public ResponseEntity<? super UserListReponseDto>userList(){
        ResponseEntity<? super UserListReponseDto> reponse = adminService.userLIst();
        return  reponse;
    }

    @GetMapping("/auth/select")
    public String authSelect(){
        return"user/select";
    }

    @GetMapping("/admin/sign-in")
    public String adminSigninPage(){
        return "user/adminLogin";
    }

    @PostMapping("/admin/sign-in")
    public String adminSignin(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword){
        SignInRequestDto requestBody = new SignInRequestDto();
        requestBody.setUserId(userId);
        requestBody.setUserPassword(userPassword);
        System.out.println("id: "+ requestBody.getUserId());
        System.out.println("pw: "+ requestBody.getUserPassword());
        boolean isCheck = adminService.adminSignIn(requestBody);
        System.out.println("check: "+isCheck);
        if(isCheck)
            return "user/admin";
        return "user/select";
    }
}
