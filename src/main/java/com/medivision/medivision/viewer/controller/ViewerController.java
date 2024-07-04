package com.medivision.medivision.viewer.controller;


import com.medivision.medivision.viewer.domain.service.ViewerService;
import com.medivision.medivision.viewer.dto.response.ViewSeriesResponseDto;
import com.medivision.pacs.entity.VImageEntity;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/viewer")
@RequiredArgsConstructor
public class ViewerController {
    private final ViewerService viewerService;

    @GetMapping("")
    public ModelAndView viewer() {
        ModelAndView modelAndView = new ModelAndView("viewer/viewer");
        return modelAndView;
    }

    @GetMapping("/{studyKey}")
    public ModelAndView findImage(@PathVariable int studyKey) {
        System.out.println(studyKey);
        System.out.println("1");
        ModelAndView mav = new ModelAndView("viewer/viewer");
        System.out.println("2");
        List<ViewSeriesResponseDto> SeriesList = new ArrayList<>();
        System.out.println("3");
        List<VSeriesEntity> seriesList = viewerService.findSeriesByStudyKey(studyKey);
        System.out.println("4");
        for(VSeriesEntity vSeriesEntity : seriesList) {
            ViewSeriesResponseDto viewSeriesResponseDto = new ViewSeriesResponseDto(vSeriesEntity);
            List<VImageEntity> imageList = viewerService.findImagesBySeriesId(viewSeriesResponseDto.getSeriesKey());
            System.out.println(imageList.size());
            viewSeriesResponseDto.setImageList(imageList);
            SeriesList.add(viewSeriesResponseDto);

        }
        System.out.println("5");
        mav.addObject("seriesArray", SeriesList);
        System.out.println("6");
        return mav;
    }
}
