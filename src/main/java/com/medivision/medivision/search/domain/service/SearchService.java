package com.medivision.medivision.search.domain.service;

import com.medivision.pacs.entity.StudyEntity;
import com.medivision.pacs.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final StudyRepository studyRepository;


    public List<StudyEntity> findAll(){
        return studyRepository.findAll();
    }
}
