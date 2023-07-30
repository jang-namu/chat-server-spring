package com.example.chat.chat.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel("채팅 응답 DTO")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponseDto {

    @ApiModelProperty(value = "채팅 식별자", notes = "Long 타입의 정수값")
    private Long id;

    @ApiModelProperty(value = "그룹 식별자", notes = "Long 타입의 정수값")
    private Long gid;

    @ApiModelProperty(value = "채팅 메세지", notes = "유저가 작성한 채팅 내용")
    private String message;

    @ApiModelProperty(value = "채팅 생성 시점", notes = "유저가 채팅을 작성한 시간")
    private LocalDateTime genTime;

}
