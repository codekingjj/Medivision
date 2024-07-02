package com.medivision.medivision.log.login.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLogEntity, Integer> {

    LoginLogEntity findByUserId(String userId);
}
