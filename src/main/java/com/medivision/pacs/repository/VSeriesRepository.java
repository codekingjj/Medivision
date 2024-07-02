package com.medivision.pacs.repository;


import com.medivision.pacs.entity.VSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VSeriesRepository extends JpaRepository<VSeriesEntity, Integer> {
    List<VSeriesEntity> findByStudyKey(int studyKey);
}
