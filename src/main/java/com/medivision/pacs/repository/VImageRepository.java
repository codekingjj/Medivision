package com.medivision.pacs.repository;

import com.medivision.pacs.entity.VImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VImageRepository   extends JpaRepository<VImageRepository, Integer> {

    List<VImageEntity> findByStudyKey(int studyKey);

}
