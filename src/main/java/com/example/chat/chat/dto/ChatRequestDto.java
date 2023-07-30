package com.example.chat.chat.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@ApiModel("채팅 요청 DTO")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequestDto {

    @ApiModelProperty(value = "그룹 식별자", notes = "채팅을 생성할 채팅방-유저 쌍 지정", required = true)
    private Long gid;

    @ApiModelProperty(value = "채팅 메세지", notes = "유저가 작성한 채팅 내용", required = true)
    private String message;

}
