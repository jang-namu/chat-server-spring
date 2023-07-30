package com.example.chat.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "유저 요청 DTO")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @ApiModelProperty(value = "유저명", example = "홍길동", required = true)
    private String name;
    @ApiModelProperty(value = "이메일", example = "example@example.com", required = true)
    private String email;
}
