package com.medivision.medivision.viewer.controller;


import com.medivision.medivision.viewer.domain.service.ViewerService;
import com.medivision.medivision.viewer.dto.response.FileResponse;
import com.medivision.medivision.viewer.dto.response.ViewImageResponseDto;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/viewer")
@RequiredArgsConstructor
public class ViewerController {
    private final ViewerService viewerService;

    @GetMapping("/{studyKey}")
    public ModelAndView findImage(@PathVariable int studyKey) {
        System.out.println(studyKey);
        ModelAndView mav = new ModelAndView("viewer/viewer");
        List<ViewSeriesResponseDto> SeriesList = new ArrayList<>();
        List<ViewImageResponseDto> ImageList = new ArrayList<>();
        List<VSeriesEntity> seriesList = viewerService.findSeriesByStudyKey(studyKey);
        System.out.println("사이즈  : " + seriesList.size());
        List<FileResponse> fileList = new ArrayList<>();
        String driver = "Z:\\";
        for(VSeriesEntity vSeriesEntity : seriesList) {
            ViewSeriesResponseDto viewSeriesResponseDto = new ViewSeriesResponseDto(vSeriesEntity);
            List<VImageEntity> imageList = viewerService.findImagesBySeriesKeyAndStudyKey(viewSeriesResponseDto.getSeriesKey(), studyKey);

            for(VImageEntity vImageEntity : imageList) {
                ViewImageResponseDto viewImageResponseDto = new ViewImageResponseDto(vImageEntity);
                String totalPath = vImageEntity.getPath() + vImageEntity.getFname();
                viewImageResponseDto.setTotalPath(totalPath);
                ImageList.add(viewImageResponseDto);
                File file = new File(viewImageResponseDto.getTotalPath());
//                try {
//                    FileResponse fileResponse = new FileResponse();
//
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
            viewSeriesResponseDto.setImageList(ImageList);
            SeriesList.add(viewSeriesResponseDto);
        }
        mav.addObject("seriesArray", SeriesList);
        return mav;
    }
}
