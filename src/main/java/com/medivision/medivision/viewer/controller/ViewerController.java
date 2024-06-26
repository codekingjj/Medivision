package com.medivision.medivision.viewer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewerController {

    @GetMapping("/viewer")
    public String viewer(Model model) {
        return "viewer/viewer";
    }
}
