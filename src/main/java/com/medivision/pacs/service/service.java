package com.medivision.pacs.service;

import com.medivision.pacs.entity.VImageEntity;
import com.medivision.pacs.repository.VImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class service {

    private final VImageRepository vImageRepository;

    public List<VImageEntity> findImage(int studyKey){
        return  vImageRepository.findByStudyKey(studyKey);
    }

}
