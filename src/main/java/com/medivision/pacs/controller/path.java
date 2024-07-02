package com.medivision.pacs.controller;

import com.medivision.pacs.entity.VImageEntity;
import com.medivision.pacs.service.VImageService;

import java.io.File;
import java.util.List;

public class path {

    VImageService vImageService;

    public String getPath(int studyKey){
        String realPath = "";

        String path;

        return  realPath;
    }


    public File getFile(String path){
        File file = new File(path);
        return file;

    }
}
