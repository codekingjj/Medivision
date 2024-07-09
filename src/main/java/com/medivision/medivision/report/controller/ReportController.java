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
    public ResponseEntity<? super ReportResponse> reports(@PathVariable("studykey") String studyKey,@AuthenticationPrincipal String code) {
        int studykey = Integer.parseInt(studyKey);
        int userCode = Integer.parseInt(code);
        ReportRequestDto reportDto = new ReportRequestDto();
        reportDto.setStudyKey(studykey);
        reportDto.setWriter(userCode);

        ResponseEntity<? super ReportResponse> response = reportService.getReportList(reportDto);
        return response;
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

    @GetMapping ("/report/update")
    public String updatePage(@RequestParam String index, Model model){
        ReportResponseDto reportResponseDto = reportService.getTarget(Integer.parseInt(index));
        model.addAttribute("report",reportResponseDto);
        return "report/update";
    }

    @PatchMapping("/report/update/{index}")
    @ResponseBody
    public ResponseEntity<? super ReportResponse> updateReport(@PathVariable("index") String index, @RequestBody ReportRequestDto reportDto, @AuthenticationPrincipal String code){
        reportDto.setWriter(Integer.parseInt(code));
        return null;
    }

}
