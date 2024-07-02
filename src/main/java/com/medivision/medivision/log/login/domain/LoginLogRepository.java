package com.medivision.medivision.log.login.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLogEntity, Integer> {

    List<LoginLogEntity> findByUserId(String userId);
}
