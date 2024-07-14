package com.medivision.medivision.user.domain.repository;

import com.medivision.medivision.user.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUserId(String userId);

    UserEntity findByUserId(String userId);

    UserEntity findByUserCode(int userCode);

    Page<UserEntity> findAllByUserIdContainingAndUserCodeNot(Pageable pageable, String userId, int userCode);
}
