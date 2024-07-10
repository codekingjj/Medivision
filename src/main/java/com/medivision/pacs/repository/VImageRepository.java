package com.medivision.pacs.repository;

import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.pacs.entity.VImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VImageRepository extends JpaRepository<VImageEntity, Integer> {

    @Query(nativeQuery = true, value = "select i.* from pacsplus.v_imagetab i where i.serieskey = :seriesKey AND i.studykey = :studyKey")
    List<VImageEntity> getImage(@Param("studyKey") int studyKey, @Param("seriesKey") int seriesKey);



}
