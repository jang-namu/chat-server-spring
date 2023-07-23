package com.example.chat.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Groups, Long> {
    List<Groups> findAllByUser_Id(Long uid);
    List<Groups> findAllByChatRoom_Id(Long roomId);
    Groups findByUser_IdAndChatRoom_Id(Long uid, Long roomId);
    void deleteByUser_IdAndChatRoom_Id(Long uid, Long roomId);
}
