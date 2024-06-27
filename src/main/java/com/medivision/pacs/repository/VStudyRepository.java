package com.medivision.pacs.repository;

import com.medivision.pacs.entity.VStudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VStudyRepository extends JpaRepository<VStudyEntity, Integer> {




    List<VStudyEntity> findByPid(String pid);

    List<VStudyEntity> findByModality(String modality);

//    되는 코드들
    List<VStudyEntity> findByPidLike(String pid);

    List<VStudyEntity> findByPnameLike(String pname);

    List<VStudyEntity> findByReportstatus(int reportstatus);


}
