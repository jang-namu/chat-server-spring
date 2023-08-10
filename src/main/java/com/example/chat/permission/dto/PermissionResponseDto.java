package com.example.chat.permission.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@ApiModel("퍼미션 응답 DTO")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponseDto {

    @ApiModelProperty(value = "그룹 식별자", notes = "채팅방-유저 쌍 식별")
    private Long gid;

    @ApiModelProperty(value = "권한 레벨")
    private Byte state;
}
