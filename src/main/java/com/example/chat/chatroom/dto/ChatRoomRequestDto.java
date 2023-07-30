package com.example.chat.chatroom.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel("채팅방 개설 요청 DTO")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomRequestDto {

    @ApiModelProperty(value = "채팅방 이름", notes = "개설할 채팅방 이름")
    private String title;

    @ApiModelProperty(value = "관리자 식별자", notes = "Long 타입의 정수값")
    private Long adminId;
}
