package com.example.chat.group;

import com.example.chat.chatroom.ChatRoom;
import com.example.chat.user.User;
import lombok.Getter;

import javax.persistence.*;

@Table(name = "group_tb")
@Entity
@Getter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "uid")
    private User user;

    @OneToOne
    @JoinColumn(name = "roomId")
    private ChatRoom chatRoom;
}
