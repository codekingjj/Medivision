package com.medivision.medivision.patientBookmark.controller;

import com.medivision.medivision.patientBookmark.domain.PatientBookmark;
import com.medivision.medivision.patientBookmark.domain.PatientBookmarkService;
import com.medivision.medivision.patientBookmark.dto.request.PatientBookmarkDeleteRequestDto;
import com.medivision.pacs.entity.VStudyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patientBookmark")
public class PatientBookmarkController {
    private final PatientBookmarkService patientBookmarkService;

    @GetMapping("")
    public String index(@AuthenticationPrincipal String code) {
        System.out.println("userCode: " + code);
        return "index";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody PatientBookmarkDeleteRequestDto pidDto) {
        System.out.println(pidDto.getPids().length);
        return "index";
    }

    @GetMapping("/all")
    @ResponseBody
    public ModelAndView findByUserCode(){
        ModelAndView mav = new ModelAndView("/patientBookmark/patientBookmark");
        int userCode = 1;

        List<PatientBookmark> patientBookmarks = patientBookmarkService.findByUserCode(userCode);
        List<VStudyEntity> studies = new ArrayList<VStudyEntity>();

        for (PatientBookmark patientBookmark : patientBookmarks) {
            String pid = patientBookmark.getPid();

            List<VStudyEntity> studiesByPid = patientBookmarkService.findByPid(pid);

            for (VStudyEntity studyByPid : studiesByPid) {
                studies.add(studyByPid);
            }
        }

        mav.addObject("studies", studies);

        return mav;
    }
}
