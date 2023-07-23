package com.example.chat.permission;

import com.example.chat.group.Groups;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "permission_tb")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @Id
    @Column(name="gid", nullable = false)
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "gid")
    private Groups groups;

    private Byte state;

}
