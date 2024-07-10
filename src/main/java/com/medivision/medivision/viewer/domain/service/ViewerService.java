package com.medivision.medivision.viewer.domain.service;

import com.medivision.medivision.viewer.dto.response.ViewSeriesResponseDto;
import com.medivision.pacs.entity.VImageEntity;
import com.medivision.pacs.entity.VSeriesEntity;
import com.medivision.pacs.repository.VImageRepository;
import com.medivision.pacs.repository.VSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ViewerService {
    private final VSeriesRepository vSeriesRepository;
    private final VImageRepository vImageRepository;

    public List<VSeriesEntity> findSeriesByStudyKey(int studyKey) {
        return vSeriesRepository.findByStudyKey(studyKey);
    }

    public List<VImageEntity> findImagesBySeriesKeyAndStudyKey(int studyKey, int seriesKey) {
        return vImageRepository.getImage(studyKey, seriesKey);
    }

}
