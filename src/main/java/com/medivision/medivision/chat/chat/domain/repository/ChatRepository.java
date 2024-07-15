package com.medivision.medivision.chat.chat.domain.repository;

import com.medivision.medivision.chat.chat.domain.entity.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Page<Chat> findByRoomIdOrderByCreateDateDesc(int roomId, Pageable pageable);

    Chat findFirstByRoomIdOrderByCreateDateDesc(int roomId);

    boolean existsByRoomId(int roomId);
}
