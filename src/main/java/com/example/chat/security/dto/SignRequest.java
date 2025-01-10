package com.example.chat.security.dto;

import lombok.Getter;

@Getter
public class SignRequest {
    private Long id;

    private String account;

    private String password;

    private String name;

    private String email;
}
