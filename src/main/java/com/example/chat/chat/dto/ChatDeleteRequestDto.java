package com.example.chat.chat.dto;

import com.example.chat.group.Groups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDeleteRequestDto {

    private Long id;

    private Groups groups;

    private String message;

}
