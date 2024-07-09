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


    @GetMapping("/get/{studyKey}")
    public ResponseEntity<List<SeriesKeyAndFileResponseDto>> findImage(@PathVariable int studyKey) {
        System.out.println(studyKey);
        List<SeriesKeyAndFileResponseDto> fileList = new ArrayList<>();
        ModelAndView mav = new ModelAndView("viewer/viewer");
        List<VSeriesEntity> seriesList = viewerService.findSeriesByStudyKey(studyKey);
        System.out.println("사이즈  : " + seriesList.size());
//        List<List<FileResponse>> fileList = new ArrayList<>();
        String driver = "Z:\\";
        for(VSeriesEntity vSeriesEntity : seriesList) {
            SeriesKeyAndFileResponseDto seriesKeyAndFileResponseDto = new SeriesKeyAndFileResponseDto();
            seriesKeyAndFileResponseDto.setSeriesKey(vSeriesEntity.getSeriesKey());
            ViewSeriesResponseDto viewSeriesResponseDto = new ViewSeriesResponseDto(vSeriesEntity);
            List<VImageEntity> imageList = viewerService.findImagesBySeriesKeyAndStudyKey(vSeriesEntity.getSeriesKey(), studyKey);
            List<FileResponse> childFileList = new ArrayList<>();
            System.out.println(imageList.size());
            System.out.println("시리즈 키 : " + vSeriesEntity.getSeriesKey());
            for(VImageEntity imageEntity : imageList) {
                System.out.println("ImageKey : " + imageEntity.getImageKey());
                System.out.println("Path : " + imageEntity.getPath());

                System.out.println("Fname : " + imageEntity.getFname());
                System.out.println("studyKey : " + imageEntity.getStudyKey());
                System.out.println("seriesKey : " + imageEntity.getSeriesKey());
            }
            for(VImageEntity vImageEntity : imageList) {

                ViewImageResponseDto viewImageResponseDto = new ViewImageResponseDto(vImageEntity);
                String totalPath = vImageEntity.getPath() + vImageEntity.getFname();
                viewImageResponseDto.setTotalPath(totalPath);
                String realPath = driver + totalPath;
                File file = new File(realPath);
                try {
                    FileResponse fileResponse = new FileResponse();
                    fileResponse.setImageKey(vImageEntity.getImageKey());
                    fileResponse.setFileName(vImageEntity.getFname());
                    fileResponse.setFileType(Files.probeContentType(file.toPath()));
                    fileResponse.setBase64Content(encodeFileToBase64(file));
                    childFileList.add(fileResponse);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            seriesKeyAndFileResponseDto.setFileList(childFileList);
            fileList.add(seriesKeyAndFileResponseDto);
        }
        mav.addObject("fileArray", fileList);
        return new ResponseEntity<>(fileList, HttpStatus.OK);
    }

    private String encodeFileToBase64(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
