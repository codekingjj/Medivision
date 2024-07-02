package com.medivision.medivision.log.login.controller;

import com.medivision.medivision.log.login.domain.LoginLogEntity;
import com.medivision.medivision.log.login.domain.service.LoginLogService;
import com.medivision.medivision.log.login.dto.LogingLogResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class logController {

    private final LoginLogService loginLogService;

    @PostMapping("/log/login")
    public ResponseEntity<? super LogingLogResponseDto> loginLog(@AuthenticationPrincipal String userCode){
        System.out.println("userCode: "+userCode);
        ResponseEntity<? super LogingLogResponseDto> log = loginLogService.loginUserLog(userCode);
        return log;
    }
    @GetMapping("/log/login")
    public String log(){
        return "log/loginLog";
    }

}
