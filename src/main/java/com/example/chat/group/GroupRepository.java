package com.example.chat.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByUser_Id(Long uid);
    List<Group> findAllByChatRoom_Id(Long roomId);
    Group findByUser_IdAndChatRoom_Id(Long uid, Long roomId);
    void deleteByUser_IdAndChatRoom_Id(Long uid, Long roomId);
}
