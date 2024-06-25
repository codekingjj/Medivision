package com.medivision.pacs.repository;

import com.medivision.pacs.entity.PacsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<PacsEntity, Integer> {


}
