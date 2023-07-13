package com.example.chat.permission;

import com.example.chat.group.Group;
import lombok.Getter;

import javax.persistence.*;

@Table(name = "permission_tb")
@Entity
@Getter
public class Permission {

    @Id
    @MapsId
    @OneToOne
    @JoinColumn(name = "gid")
    private Group group;

    private Byte state;

}
