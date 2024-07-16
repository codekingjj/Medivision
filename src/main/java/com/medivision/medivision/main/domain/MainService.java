package com.medivision.medivision.main.domain;

import com.medivision.pacs.entity.StudyEntity;
import com.medivision.pacs.entity.VStudyEntity;
import com.medivision.pacs.repository.StudyRepository;
import com.medivision.pacs.repository.VStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {

    public final StudyRepository studyRepository;


    public List<StudyEntity> findAll(){
        return studyRepository.findAll();
    }

}
