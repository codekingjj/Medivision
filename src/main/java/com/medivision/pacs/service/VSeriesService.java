package com.medivision.pacs.service;

import com.medivision.pacs.entity.VSeriesEntity;
import com.medivision.pacs.repository.VSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class VSeriesService {

    private final VSeriesRepository vSeriesRepository;

    public List<VSeriesEntity> findStudyKey(int studyKey){
        return vSeriesRepository.findByStudyKey(studyKey);
    }

}
