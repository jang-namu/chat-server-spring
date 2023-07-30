package com.example.chat.group.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("그룹 요청 DTO")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequestDto {

    @ApiModelProperty(value = "유저 식별자", notes = "Long 타입의 정수값", required = true)
    private Long uid;

    @ApiModelProperty(value = "채팅방 식별자", notes = "Long 타입의 정수값", required = true)
    private Long roomId;

}
