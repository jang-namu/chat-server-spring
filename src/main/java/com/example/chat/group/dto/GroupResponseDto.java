package com.example.chat.group.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@ApiModel("그룹 응답 DTO")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponseDto {

    @ApiModelProperty(value = "그룹 식별자", notes = "Long 타입의 정수값")
    private Long id;

    @ApiModelProperty(value = "유저 식별자", notes = "Long 타입의 정수값")
    private Long uid;

    @ApiModelProperty(value = "채팅방 식별자", notes = "Long 타입의 정수값")
    private Long roomId;
}
