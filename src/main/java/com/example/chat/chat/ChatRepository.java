package com.example.chat.chat;

import com.example.chat.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByGroup(Group group);
}
