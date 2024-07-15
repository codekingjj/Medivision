package com.medivision.medivision.report.controller;

import com.medivision.medivision.jwt.JwtProvider;
import com.medivision.medivision.log.report.domain.service.ReportLogService;
import com.medivision.medivision.report.domain.service.ReportService;
import com.medivision.medivision.report.dto.ReportRequestDto;
import com.medivision.medivision.report.dto.ReportResponse;
import com.medivision.medivision.report.dto.ReportResponseDto;
import com.medivision.pacs.entity.StudyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final ReportLogService reportLogService;

    @GetMapping("/reports/{studykey}")
    public ResponseEntity<? super ReportResponse> reports(@PathVariable("studykey") String studyKey,@AuthenticationPrincipal String code) {
        int studykey = Integer.parseInt(studyKey);
        if("anonymousUser".equals(code)) return ReportResponse.differentUser();
        int userCode = Integer.parseInt(code);
        ReportRequestDto reportDto = new ReportRequestDto();
        reportDto.setStudyKey(studykey);
        reportDto.setWriter(userCode);

        ResponseEntity<? super ReportResponse> response = reportService.getReportList(reportDto);
        return response;
    }

    @PostMapping("/createReport")
    public ResponseEntity<? super ReportResponse> report(@RequestBody ReportRequestDto reportDto,@AuthenticationPrincipal String code) {
        reportDto.setWriter(Integer.parseInt(code));
        ResponseEntity<? super ReportResponse> response = reportService.createReport(reportDto);
        return response;
    }

    @GetMapping("/reportPage")
    public String reportPage(){
        return "report/report";
    }

    @GetMapping("/report/targetReport")
    public String targetPage(@RequestParam("index") String index,@RequestParam("userCode") String userCode, Model model, HttpServletRequest request){
        ReportResponseDto reportResponseDto = reportService.getTarget(Integer.parseInt(index));
        JwtProvider jwtProvider = new JwtProvider();
        String user = jwtProvider.validate(userCode);
        model.addAttribute("report", reportResponseDto);
        String ip = request.getRemoteAddr();
        reportLogService.reportRead(reportResponseDto.getReportIndex(), user, ip);
        return "report/targetReport";
    }

    @GetMapping ("/report/update")
    public String updatePage(@RequestParam String index, Model model){
        ReportResponseDto reportResponseDto = reportService.getTarget(Integer.parseInt(index));
        model.addAttribute("report",reportResponseDto);
        return "report/update";
    }

    @PutMapping("/report/update/{index}")
    @ResponseBody
    public ResponseEntity<? super ReportResponse> updateReport(@PathVariable("index") String index, @RequestBody ReportRequestDto reportDto, @AuthenticationPrincipal String code){
        reportDto.setWriter(Integer.parseInt(code));
        int reportIndex = Integer.parseInt(index);
        ResponseEntity<? super ReportResponse> response = reportService.updateReport(reportDto, reportIndex);
        return response;
    }

}
