package com.example.chat.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponseDto {
    private Long id;

    private Long gid;

    private String message;

    private LocalDateTime genTime;

    private Date endDate;   // Date
}
