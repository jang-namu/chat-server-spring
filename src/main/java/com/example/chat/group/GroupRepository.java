package com.example.chat.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAllByUid(Long uid);
    List<Group> findAllByRoomId(Long roomId);
    List<Group> findAllByUidAndRoomId(Long uid, Long roomId);
}
