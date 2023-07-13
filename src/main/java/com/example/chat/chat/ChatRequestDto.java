package com.example.chat.chat;

import com.example.chat.group.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequestDto {
    private Group group;
    private String message;
}
