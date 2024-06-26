package com.medivision.medivision.patientBookmark.domain;

import com.medivision.pacs.entity.VStudyEntity;
import com.medivision.pacs.repository.VStudyRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientBookmarkService {
    private final VStudyRepository studyRepository;

    public List<VStudyEntity> findAll() {
        return studyRepository.findAll();
    }
}
