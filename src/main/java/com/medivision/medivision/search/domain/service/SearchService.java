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


    public List<VStudyEntity> findByPidLike(String pid){
        return studyRepository.findByPidLike("%" + pid + "%");
    }

    public List<VStudyEntity> findByReportstatus(int reportStatus){
        return studyRepository.findByReportstatus(reportStatus);
    }


    public List<VStudyEntity> findByModality(String modality){
        return studyRepository.findByModality(modality);
    }


    public List<VStudyEntity> findByPnameLike(String pname){
        return studyRepository.findByPnameLike("%" + pname + "%");
    }


}
