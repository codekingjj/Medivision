package com.medivision.pacs.service;

import com.medivision.pacs.entity.PatientEntity;
import com.medivision.pacs.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientEntity findByPid(String pid){
        return patientRepository.findByPid(pid);
        }

    public List<PatientEntity> findByPidOrPname(String pid, String pname){
        if (pid.isEmpty() && pname.isEmpty())
            return patientRepository.findAll();

        if (pid.isEmpty())
            return patientRepository.findByPnameContaining(pname);

        if (pname.isEmpty())
            return patientRepository.findByPidContaining(pid);

        return patientRepository.findByPidContainingOrPnameContaining(pid, pname);
    }
}
