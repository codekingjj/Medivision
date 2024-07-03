package com.medivision.medivision.viewer.domain.service;

import com.medivision.pacs.entity.VSeriesEntity;
import com.medivision.pacs.repository.VSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ViewerService {
    private final VSeriesRepository vSeriesRepository;

    public List<VSeriesEntity> findSeriesByStudyKey(int studyKey) {
        return vSeriesRepository.findByStudyKey(studyKey);
    }

}
