package com.medivision.medivision.patientBookmark.controller;

import com.medivision.medivision.patientBookmark.domain.entity.PatientBookmark;
import com.medivision.medivision.patientBookmark.domain.service.PatientBookmarkService;
import com.medivision.medivision.patientBookmark.dto.request.PatientBookmarkAddRequestDto;
import com.medivision.medivision.patientBookmark.dto.request.PatientBookmarkDeleteRequestDto;
import com.medivision.medivision.patientBookmark.dto.request.PatientBookmarkSearchRequestDto;
import com.medivision.medivision.patientBookmark.dto.response.PatientBookmarkResponseDto;
import com.medivision.pacs.entity.PatientEntity;
import com.medivision.pacs.entity.VStudyEntity;
import com.medivision.pacs.repository.VStudyRepository;
import com.medivision.pacs.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/patientBookmark")
@RequiredArgsConstructor
public class PatientBookmarkController {
    private final PatientBookmarkService patientBookmarkService;
    private final VStudyRepository vStudyRepository;
    private final PatientService patientService;

    @GetMapping("")
    public String patientBookmarkPage() {
        return "patientBookmark/patientBookmark";
    }

    @GetMapping("/all")
    public ResponseEntity<List<VStudyEntity>> findAll(@AuthenticationPrincipal String userCode) {
        List<PatientBookmark> patientBookmarks = patientBookmarkService.findByUserCode(Integer.parseInt(userCode));
        List<VStudyEntity> studies = new ArrayList<>();

        for (PatientBookmark patientBookmark : patientBookmarks) {
            String pid = patientBookmark.getPid();
            List<VStudyEntity> studiesByPid = vStudyRepository.findByPid(pid);

            studies.addAll(studiesByPid);
        }

        return new ResponseEntity<>(studies, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PatientBookmarkResponseDto>> findByUserCode(@AuthenticationPrincipal String userCode) {
        List<PatientBookmark> patientBookmarks = patientBookmarkService.findByUserCode(Integer.parseInt(userCode));
        List<PatientBookmarkResponseDto> patientBookmarkResponseDtoList = new ArrayList<>();

        for (PatientBookmark patientBookmark : patientBookmarks) {
            PatientEntity patient = patientService.findByPid(patientBookmark.getPid());

            PatientBookmarkResponseDto patientBookmarkResponseDto = new PatientBookmarkResponseDto();

            patientBookmarkResponseDto.setPid(patientBookmark.getPid());
            patientBookmarkResponseDto.setPname(patient.getPname());
            patientBookmarkResponseDto.setPbirthdate(patient.getPbirthdate());
            patientBookmarkResponseDto.setPsex(patient.getPsex());

            patientBookmarkResponseDtoList.add(patientBookmarkResponseDto);
        }

        return new ResponseEntity<>(patientBookmarkResponseDtoList, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<PatientBookmarkResponseDto>> findByPatientPidOrPname(@RequestBody PatientBookmarkSearchRequestDto searchRequestDto, @AuthenticationPrincipal String userCode) {
        String searchPid = searchRequestDto.getPid();
        String searchPname = searchRequestDto.getPname();

        List<PatientBookmarkResponseDto> patientBookmarkResponseDtoList = new ArrayList<>();
        List<PatientEntity> patients = patientService.findByPidOrPname(searchPid, searchPname);

        System.out.println(patients.size());

        for (PatientEntity patient : patients) {
            PatientBookmark patientBookmark = patientBookmarkService.findByUserCodeAndPid(Integer.parseInt(userCode), patient.getPid());

            if (patientBookmark == null)
                continue;

            PatientBookmarkResponseDto patientBookmarkResponseDto = new PatientBookmarkResponseDto();
            patientBookmarkResponseDto.setPid(patientBookmark.getPid());
            patientBookmarkResponseDto.setPname(patient.getPname());
            patientBookmarkResponseDto.setPbirthdate(patient.getPbirthdate());
            patientBookmarkResponseDto.setPsex(patient.getPsex());

            patientBookmarkResponseDtoList.add(patientBookmarkResponseDto);
        }

        return new ResponseEntity<>(patientBookmarkResponseDtoList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addByPids(@RequestBody PatientBookmarkAddRequestDto pidDto, @AuthenticationPrincipal String userCode) {
        return patientBookmarkService.addByUserCodeAndPids(Integer.parseInt(userCode), pidDto.getPids());
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteByPids(@RequestBody PatientBookmarkDeleteRequestDto pidDto) {
        return patientBookmarkService.deleteByPids(pidDto.getPids());
    }
}