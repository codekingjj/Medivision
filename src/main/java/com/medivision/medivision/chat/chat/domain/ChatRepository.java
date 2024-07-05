package com.medivision.medivision.chat.chat.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public Page<Chat> findByRoomIdOrderByCreateDateDesc(int roomId, Pageable pageable);
}
