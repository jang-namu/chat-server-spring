package com.example.chat.permission;

import com.example.chat.group.Group;
import lombok.*;

import javax.persistence.*;

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
    private Group group;

    private Byte state;

}
