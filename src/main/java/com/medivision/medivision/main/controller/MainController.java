package com.medivision.medivision.main.controller;

import com.medivision.medivision.main.domain.MainService;
import com.medivision.pacs.entity.VStudyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final MainService mainService;

    @GetMapping()
    public String main() {
        return "main/main";
    }

    @GetMapping("setting")
    @ResponseBody
    public List<VStudyEntity> setting() {
        return mainService.findAll();
    }
}
