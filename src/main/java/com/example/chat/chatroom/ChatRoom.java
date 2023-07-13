package com.example.chat.chatroom;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;


@Table(name = "chatroom_tb")
@Entity
@Getter
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date genTime;

    private String title;

    private Long adminId;
}
