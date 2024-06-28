package com.medivision.medivision.patientBookmark.domain;

import com.medivision.medivision.patientBookmark.dto.request.PatientBookmarkRequestDto;
import com.medivision.pacs.entity.VStudyEntity;
import com.medivision.pacs.repository.VStudyRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Transactional
    public ResponseEntity<String> addByUserCodeAndPids(int userCode, String[] pids) {
        List<PatientBookmark> patientBookmarks = new ArrayList<>();

        for (String pid : pids) {
            PatientBookmarkRequestDto patientBookmarkDto = new PatientBookmarkRequestDto();
            patientBookmarkDto.setUserCode(userCode);
            patientBookmarkDto.setPid(pid);

            PatientBookmark patientBookmark = new PatientBookmark(patientBookmarkDto);

            patientBookmarks.add(patientBookmark);
        }

         patientBookmarkRepository.saveAll(patientBookmarks);

        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);

        return response;
    }

    @Transactional
    public ResponseEntity<String> deleteByPids(String[] pids) {
        patientBookmarkRepository.deleteByPidIn(Arrays.asList(pids));

        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);

        return response;
    }
}
