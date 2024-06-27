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

    public List<PatientBookmark> findByUserCode(int userCode) {
        return patientBookmarkRepository.findByUserCode(userCode);
    }

    public List<PatientBookmark> findPatientAllUserCode(int userCode) {
        //return patientBookmarkRepository.findPatientAllByUserCode(userCode);
        return null;
    }
}
