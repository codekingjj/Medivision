package com.medivision.medivision.log.study.controller;

import com.medivision.medivision.log.study.domain.service.StudyLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLOutput;

@Controller
@RequiredArgsConstructor
public class StudyLogController {

    private final StudyLogService studyLogService;

    @GetMapping("/log/{studyKey}")
    public void studyKey(@PathVariable("studyKey") String studyKey, @AuthenticationPrincipal String userCode, HttpServletRequest request){
        String ip = request.getRemoteAddr();
        studyLogService.saveStudyLog(userCode, studyKey, ip);
    }
}
