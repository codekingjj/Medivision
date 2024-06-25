package com.medivision.medivision.search.domain.service;

import com.medivision.pacs.entity.VStudyEntity;
import com.medivision.pacs.repository.VStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final VStudyRepository studyRepository;


    public List<VStudyEntity> findAll(){
        return studyRepository.findAll();
    }
}
