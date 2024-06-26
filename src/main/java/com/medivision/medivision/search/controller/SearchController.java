package com.medivision.medivision.search.controller;

import com.medivision.medivision.search.domain.service.SearchService;
import com.medivision.medivision.search.dto.request.SearchRequestDto;
import com.medivision.pacs.entity.VStudyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping()
    public String search(){
        return "search/search";
    }

    @GetMapping("findall")
    @ResponseBody
    public List<VStudyEntity> findAll(){
        return searchService.findAll();
    }

    @GetMapping("detail")
    @ResponseBody
    public List<VStudyEntity> findById(@ModelAttribute SearchRequestDto searchRequestDto){
        System.out.println("시작");
        List<VStudyEntity> result = new ArrayList<>();

        List<VStudyEntity> findPid;
        List<VStudyEntity> findPName;
        List<VStudyEntity> findReportStatus;
        List<VStudyEntity> findModality;

        String pid = searchRequestDto.getPid();
        String pname = searchRequestDto.getPname();
        System.out.println(pname);
        int reportstatus = searchRequestDto.getReportstatus();
        String modality = searchRequestDto.getModality();

        if(!(pid == null || pid.isEmpty())){
            findPid = searchService.findByPidLike(pid);
        }

        if(!(pname == null || pname.isEmpty())){
            findPName = searchService.findByPnameLike(pname);
        }

        if(!(reportstatus == -1)){
            findReportStatus = searchService.findByReportstatus(reportstatus);
        }

        if(!(modality == null || modality.isEmpty())){
            findModality = searchService.findByModality(modality);
        }

        System.out.println(pname);
        result = searchService.findByModality(modality);

        return  result;
    }

}
