package com.example.chat.chat;

import com.example.chat.group.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByGroups_Id(Groups groups);
}
