package com.example.chat.chatroom.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomRequestDto {

    private String title;

    private Long adminId;
}
