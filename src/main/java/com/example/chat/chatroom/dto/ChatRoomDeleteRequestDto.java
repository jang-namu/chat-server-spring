package com.example.chat.chatroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDeleteRequestDto {
    private Long id;

    private Long adminId;
}
