package com.medivision.medivision.alarm.controller;

import com.medivision.medivision.alarm.domain.service.AlarmService;
import com.medivision.medivision.alarm.dto.AlarmReponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {

    private final AlarmService service;

    @GetMapping("")
    public ResponseEntity<? super AlarmReponseDto> getList(@AuthenticationPrincipal String userCode){
        ResponseEntity<? super AlarmReponseDto> response = service.getAlarmList(userCode);
        return response;
    }

}
