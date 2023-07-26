package com.example.chat.chat.dto;

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

    // HTTP 헤더에 삭제 요청 유저정보를 읽어서, service를 통해 자신의 채팅만 delete 할 수 있다.
}
