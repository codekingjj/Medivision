package com.medivision.medivision.decode.domain.repository;

import com.medivision.medivision.decode.domain.entity.DecodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecodeRepository extends JpaRepository<DecodeEntity,Integer> {
    DecodeEntity findByStudyKey(int studyKey);
}
