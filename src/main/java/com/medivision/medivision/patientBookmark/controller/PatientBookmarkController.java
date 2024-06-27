package com.medivision.medivision.patientBookmark.controller;

import com.medivision.medivision.patientBookmark.domain.PatientBookmark;
import com.medivision.medivision.patientBookmark.domain.PatientBookmarkService;
import com.medivision.pacs.entity.VStudyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/all")
    public List<PatientBookmark> findByUserCode(){
        int userCode = 1;
        List<PatientBookmark> patientBookmarks = patientBookmarkService.findByUserCode(userCode);

        return patientBookmarks;
    }
}
