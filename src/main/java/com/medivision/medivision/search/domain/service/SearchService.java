package com.medivision.medivision.search.domain.service;

import com.medivision.pacs.entity.StudyEntity;
import com.medivision.pacs.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final StudyRepository studyRepository;


    public List<StudyEntity> findAll(){
        return studyRepository.findAll();
    }


    public List<StudyEntity> findByPidLike(String pid){
        return studyRepository.findByPidLike("%" + pid + "%");
    }

    public List<StudyEntity> findByReportstatus(int reportStatus){
        return studyRepository.findByReportstatus(reportStatus);
    }


    public List<StudyEntity> findByModality(String modality){
        return studyRepository.findByModality(modality);
    }


    public List<StudyEntity> findByPnameLike(String pname){
        return studyRepository.findByPnameLike("%" + pname + "%");
    }

    public List<StudyEntity> findDateSearch(int startDate, int endDate){
        List<StudyEntity> temp = studyRepository.findAll();
        List<StudyEntity> result = new ArrayList<>();

        for(int i=0; i<temp.size(); i++){
            int target = dateformat(temp.get(i).getStudydate());

            if(target >= startDate && target <= endDate){
                result.add(temp.get(i));
            }
        }


        return result;
    }

    public int dateformat(String date){
        int result = Integer.parseInt(date.replace("-",""));
        return result;
    }


}
