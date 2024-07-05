package com.medivision.medivision.user.domain.repository;

import com.medivision.medivision.user.domain.entity.AdminEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

     AdminEntity findByUserCode(int userCode);

     @Query(nativeQuery = true, value = "SELECT a.* FROM chat_room_and_members c JOIN admin a ON c.user_code = a.user_code WHERE c.room_id = ?1")
     List<AdminEntity> findAllByRoomId(int roomId);

     Page<AdminEntity> findAllByUserNameContaining(Pageable pageable, String userName);
}
