package com.medivision.medivision.search.controller;

import com.medivision.medivision.search.domain.service.SearchService;
import com.medivision.pacs.entity.VStudyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
