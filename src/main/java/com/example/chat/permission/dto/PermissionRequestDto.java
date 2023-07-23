package com.example.chat.permission.dto;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequestDto {

    private Long uid;

    private Long roomId;

    private Byte state;
}
