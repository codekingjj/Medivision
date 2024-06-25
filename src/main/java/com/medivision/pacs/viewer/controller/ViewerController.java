package com.medivision.pacs.viewer.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ViewerController {

    @GetMapping("/viewer")
    public String viewer(Model model) {
        return "viewer/viewer";
    }
}
