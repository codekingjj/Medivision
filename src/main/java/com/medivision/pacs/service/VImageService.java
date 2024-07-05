package com.medivision.pacs.service;

import com.medivision.pacs.entity.VImageEntity;
import com.medivision.pacs.repository.VImageRepository;
import com.medivision.pacs.repository.VSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VImageService {
    private final VImageRepository vImageRepository;

}
