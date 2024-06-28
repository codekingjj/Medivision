package com.medivision.medivision.patientBookmark.controller;

import com.medivision.medivision.patientBookmark.domain.PatientBookmark;
import com.medivision.medivision.patientBookmark.domain.PatientBookmarkService;
import com.medivision.medivision.patientBookmark.dto.request.PatientBookmarkAddRequestDto;
import com.medivision.medivision.patientBookmark.dto.request.PatientBookmarkDeleteRequestDto;
import com.medivision.pacs.entity.VStudyEntity;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patientBookmark")
@RequiredArgsConstructor
public class PatientBookmarkController {
    private final PatientBookmarkService patientBookmarkService;

    @GetMapping("")
    public String index() {
        System.out.println("userCode: ");
        return "index";
    }

    @GetMapping("/all")
    public ModelAndView findByUserCode() {
        ModelAndView mav = new ModelAndView("/patientBookmark/patientBookmark");
        // System.out.println(userCode);
        String userCode = "1";

        List<PatientBookmark> patientBookmarks = patientBookmarkService.findByUserCode(Integer.parseInt(userCode));
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

    @PostMapping("/add")
    public ResponseEntity<String> addByPids(@RequestBody PatientBookmarkAddRequestDto pidDto) {
        int userCode = 1;

        return patientBookmarkService.addByUserCodeAndPids(userCode, pidDto.getPids());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteByPids(@RequestBody PatientBookmarkDeleteRequestDto pidDto) {
        return patientBookmarkService.deleteByPids(pidDto.getPids());
        //System.out.println(pidDto.getPids().length);
    }
}
