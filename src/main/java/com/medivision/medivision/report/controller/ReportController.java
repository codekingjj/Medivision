package com.medivision.medivision.report.controller;

import com.medivision.medivision.report.domain.service.ReportService;
import com.medivision.medivision.report.dto.ReportRequestDto;
import com.medivision.medivision.report.dto.ReportResponse;
import com.medivision.medivision.report.dto.ReportResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/reports/{studykey}")
    public ResponseEntity<? super ReportResponse> reports(@PathVariable("studykey") String studyKey) {
        return reportService.getReportList(Integer.parseInt(studyKey));
    }

//    @GetMapping("/report/{reportIndex}")
//    public ResponseEntity<? super ReportResponse> targetReport(@PathVariable("reportIndex") String reportIndex ) {
//        return reportService.getReport(Integer.parseInt(reportIndex));
//    }

    @PostMapping("/createReport")
    public ResponseEntity<? super ReportResponse> report(@RequestBody ReportRequestDto reportDto,@AuthenticationPrincipal String code) {
        reportDto.setWriter(Integer.parseInt(code));
        return reportService.createReport(reportDto);
    }

    @GetMapping("/reportPage")
    public String reportPage(){
        return "report/report";
    }

    @GetMapping("/report/targetReport")
    public String targetPage(@RequestParam String index, Model model){
        ReportResponseDto reportResponseDto = reportService.getTarget(Integer.parseInt(index));
        System.out.println(index);
        model.addAttribute("report", reportResponseDto);
        return "report/targetReport";
    }
}
