package com.medivision.medivision.user.domain.repository;

import com.medivision.medivision.user.domain.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

     AdminEntity findByUserCode(int userCode);

}
