package com.medivision.medivision.log.study.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLOutput;

@Controller
public class StudyLogController {

    @GetMapping("/log/{studyKey}")
    public void studyKey(@PathVariable("studyKey") String studyKey, @AuthenticationPrincipal String userCode){
        System.out.println("여기");
        System.out.println("key:" + studyKey);
        System.out.println("userCode"+userCode);
    }
}
