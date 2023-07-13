package com.example.chat.chat;


import com.example.chat.group.Group;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "chat_tb")
@Entity
@Getter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gid")
    private Group group;

    private String message;

    private LocalDateTime genTime;

    private Date endDate;
}
