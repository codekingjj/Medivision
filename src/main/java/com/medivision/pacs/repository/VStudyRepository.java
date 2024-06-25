package com.medivision.pacs.repository;

import com.medivision.pacs.entity.VStudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VStudyRepository extends JpaRepository<VStudyEntity, Integer> {


}
