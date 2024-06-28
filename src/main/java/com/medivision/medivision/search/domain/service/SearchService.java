package com.medivision.medivision.search.domain.service;

import com.medivision.pacs.entity.VStudyEntity;
import com.medivision.pacs.repository.VStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<VStudyEntity> findDateSearch(int startDate, int endDate){
        List<VStudyEntity> temp = findAll();
        List<VStudyEntity> result = new ArrayList<>();

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
