package com.example.chat.permission.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel("퍼미션 요청 DTO")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequestDto {

    @ApiModelProperty(value = "유저 식별자", notes = "Long 타입 정수값", required = true)
    private Long uid;

    @ApiModelProperty(value = "채팅방 식별자", notes = "Long 타입 정수값", required = true)
    private Long roomId;

    @ApiModelProperty(value = "권한 레벨", notes = "Byte 타입 정수값(1=관리자)", example = "1", required = true)
    private Byte state;
}
