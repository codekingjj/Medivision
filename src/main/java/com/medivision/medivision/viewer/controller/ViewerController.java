package com.medivision.medivision.viewer.controller;


import com.medivision.medivision.viewer.domain.service.ViewerService;
import com.medivision.medivision.viewer.dto.response.FileResponse;
import com.medivision.medivision.viewer.dto.response.SeriesKeyAndFileResponseDto;
import com.medivision.medivision.viewer.dto.response.ViewImageResponseDto;
import com.medivision.medivision.viewer.dto.response.ViewSeriesResponseDto;
import com.medivision.pacs.entity.VImageEntity;
import com.medivision.pacs.entity.VSeriesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/viewer")
@RequiredArgsConstructor
public class ViewerController {
    private final ViewerService viewerService;

    @GetMapping("/{studyKey}")
        public ModelAndView viewer(@PathVariable int studyKey) {
        ModelAndView modelAndView = new ModelAndView("viewer/viewer");
        modelAndView.addObject("studyKey", studyKey);
        return modelAndView;
    }


    @GetMapping("/get/{studyKey}/{seriesKey}")
    public ResponseEntity<List<String>> findImages(@PathVariable int studyKey, @PathVariable int seriesKey) {
        List<VImageEntity> imageEntityList = viewerService.findImagesBySeriesKeyAndStudyKey(studyKey, seriesKey);
        List<String> fileList = new ArrayList<>();
        for(VImageEntity imageEntity : imageEntityList) {
            String driver = "Z:\\";
            String path = imageEntity.getPath();
            String fileName = imageEntity.getFname();
            String realPath = driver + path + fileName;
            File file = new File(realPath);
            try {
                String base64Content = encodeFileToBase64(file);
                fileList.add(base64Content);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(fileList, HttpStatus.OK);
    }

    @GetMapping("/get/{studyKey}")
    public ResponseEntity<List<Integer>> findSeriesKey(@PathVariable int studyKey) {
        List<Integer> seriesKey = new ArrayList<>();
        List<VSeriesEntity> seriesList = viewerService.findSeriesByStudyKey(studyKey);
        for (VSeriesEntity series : seriesList) {
            seriesKey.add(series.getSeriesKey());
        }
        return new ResponseEntity<>(seriesKey, HttpStatus.OK);
    }

    private String encodeFileToBase64(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
