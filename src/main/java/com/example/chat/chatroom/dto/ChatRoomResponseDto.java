package com.example.chat.chatroom.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("채팅방 응답 DTO")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomResponseDto {

    @ApiModelProperty(value = "채팅방 식별자", notes = "Long 타입의 정수값")
    private Long id;

    @ApiModelProperty(value = "채팅방 이름", notes = "지정된 채팅방 이름")
    private String title;

    @ApiModelProperty(value = "관리자 식별자", notes = "Long 타입의 정수값")
    private Long adminId;

}
