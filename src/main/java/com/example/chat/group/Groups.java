package com.example.chat.group;

import com.example.chat.chatroom.ChatRoom;
import com.example.chat.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "group_tb")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private ChatRoom chatRoom;
}
