package com.example.chat.chatroom.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("채팅방 삭제 요청 DTO")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDeleteRequestDto {

    @ApiModelProperty(value = "채팅방 식별자", notes = "Long 타입의 정수값", required = true)
    private Long id;

    @ApiModelProperty(value = "관리자 식별자", notes = "Long 타입의 정수값", required = true)
    private Long adminId;
}
