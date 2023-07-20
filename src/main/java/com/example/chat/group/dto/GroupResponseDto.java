package com.example.chat.group.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponseDto {
    private Long id;
    private Long uid;
    private Long roomId;
}
