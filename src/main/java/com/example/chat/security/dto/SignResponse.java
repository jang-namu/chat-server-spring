package com.example.chat.security.dto;

import com.example.chat.user.Authority;
import com.example.chat.user.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignResponse {

    private Long id;

    private String account;

    private String name;

    private String email;

    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    private String token;

    public SignResponse(User user) {
        this.id = user.getId();
        this.account = user.getAccount();
        this.name = user.getName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
