package com.example.chat.user.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@ApiModel("유저 응답 DTO")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    @ApiModelProperty(value = "유저 식별자", notes = "Long 타입의 정수값")
    private Long id;

    @ApiModelProperty(value = "유저명", example = "홍길동")
    private String name;

    @ApiModelProperty(value = "이메일", example = "example@example.com")
    private String email;
}
