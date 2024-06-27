package com.medivision.medivision.patientBookmark.domain;

import com.medivision.pacs.entity.VStudyEntity;
import com.medivision.pacs.repository.VStudyRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientBookmarkService {
    private final PatientBookmarkRepository patientBookmarkRepository;
    private final VStudyRepository vStudyRepository;

    public List<PatientBookmark> findByUserCode(int userCode) {
        return patientBookmarkRepository.findByUserCode(userCode);
    }

    public List<VStudyEntity> findByPid(String pid) {
        return vStudyRepository.findByPid(pid);
    }
}
