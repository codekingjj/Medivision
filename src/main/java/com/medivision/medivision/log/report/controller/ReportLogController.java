package com.medivision.medivision.log.report.controller;

import com.medivision.medivision.log.report.domain.service.ReportLogService;
import com.medivision.medivision.log.report.dto.ReportLogResponseDto;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ReportLogController {

    private final ReportLogService reportLogService;

    @PostMapping("/log/reportRead")
    public ResponseEntity<? super ReportLogResponseDto> reportLog(@AuthenticationPrincipal String userCode){
        ResponseEntity<? super ReportLogResponseDto> response = reportLogService.reportLog(userCode);
        return response;
    }

}
