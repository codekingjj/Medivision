package com.medivision.medivision.log.admin.controller;

import com.medivision.medivision.log.admin.domain.AdminLogService;
import com.medivision.medivision.log.login.domain.LoginLogEntity;
import com.medivision.medivision.log.report.domain.ReportLogEntity;
import com.medivision.medivision.log.study.domain.StudyLogEntity;
import com.medivision.medivision.user.domain.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminLogController {

    private final AdminLogService adminLogService;

    @GetMapping("/admin/log/login")
    private String loginList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model){
        int pageSize = 12;
        int blockPage = 10;

        Page<LoginLogEntity> loginList = adminLogService.loginList(pageNum,pageSize);
        long totalCountLong = adminLogService.getTotalCountLogin();
        int totalCount = (int)totalCountLong;
        String pagingImg = Paging.pagingStr(totalCount, pageSize, blockPage, pageNum, "/admin/log/login");
        model.addAttribute("loginLists", loginList.getContent());
        model.addAttribute("pagingImg", pagingImg);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNum", pageNum);
        return "log/adminLoginLog";
    }

    @GetMapping("/admin/log/study")
    private String studyList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model){
        int pageSize = 12;
        int blockPage = 10;

        Page<StudyLogEntity> studyList = adminLogService.studyList(pageNum,pageSize);
        long totalCountLong = adminLogService.getTotalCountStudy();
        int totalCount = (int)totalCountLong;
        String pagingImg = Paging.pagingStr(totalCount, pageSize, blockPage, pageNum, "/admin/log/study");
        model.addAttribute("studyLists", studyList.getContent());
        model.addAttribute("pagingImg", pagingImg);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNum", pageNum);
        return "log/adminStudyLog";
    }

    @GetMapping("/admin/log/report")
    private String reportList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model){
        int pageSize = 12;
        int blockPage = 10;

        Page<ReportLogEntity> reportList = adminLogService.reportList(pageNum,pageSize);
        long totalCountLong = adminLogService.getTotalCountReport();
        int totalCount = (int)totalCountLong;
        String pagingImg = Paging.pagingStr(totalCount, pageSize, blockPage, pageNum, "/admin/log/report");
        model.addAttribute("reportLists", reportList.getContent());
        model.addAttribute("pagingImg", pagingImg);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNum", pageNum);
        return "log/adminReportLog";
    }

}
