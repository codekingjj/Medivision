package com.medivision.medivision.viewer.controller;


import com.medivision.medivision.viewer.domain.service.ViewerService;
import com.medivision.pacs.entity.VSeriesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/viewer")
@RequiredArgsConstructor
public class ViewerController {
    private final ViewerService viewerService;

    @GetMapping("")
    public String viewer(Model model) {
        return "viewer/viewer";
    }

    @GetMapping("/{studyKey}")
    public List<VSeriesEntity> findeImage(@PathVariable("studyKey") int studyKey) {
        ModelAndView mav = new ModelAndView("/viewer/viewer");
        return viewerService.findSeriesByStudyKey(studyKey);
    }
}
