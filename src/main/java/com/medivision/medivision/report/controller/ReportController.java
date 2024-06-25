package com.medivision.medivision.report.controller;

import com.medivision.medivision.report.domain.service.ReportService;
import com.medivision.medivision.report.dto.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/reports{studykey}")
    public ResponseEntity<? super ReportResponse> reports(@PathVariable("studykey") String studyKey) {
        return reportService.getReportList(Integer.parseInt(studyKey));
    }

    @GetMapping("/report{reportIndex}")
    public ResponseEntity<? super ReportResponse> targetReport(@PathVariable("reportIndex") String reportIndex ) {
        return reportService.getReport(Integer.parseInt(reportIndex));
    }

//    @PostMapping("/report")
//    public List<? super ReportResponseDto> report() {
//        return ;
//    }
}
