package com.medivision.medivision.user.domain.repository;

import com.medivision.medivision.user.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUserId(String userId);

    UserEntity findByUserId(String userId);

    UserEntity findByUserCode(int userCode);

}
