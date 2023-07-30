package com.example.chat.user;

import com.example.chat.user.dto.UserRequestDto;
import com.example.chat.user.dto.UserResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"User"})
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @ApiOperation(value = "유저 정보 조회 api", notes = "모든 유저 정보 조회")
    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtos);
    }

    @ApiOperation(value = "유저 정보 조회 api", notes = "유저 식별자에 해당하는 유저 조회")
    @GetMapping("/{uid}")
    public ResponseEntity<UserResponseDto> getUser(
            @ApiParam(value = "유저 식별자", required = true) @PathVariable Long uid
    ) {
        UserResponseDto userResponseDto = userService.getUser(uid);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @ApiOperation(value = "유저 정보 생성 api", notes = "신규 유저 가입")
    @PostMapping()
    public ResponseEntity<UserResponseDto> postUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto savedUserResponseDto = userService.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(savedUserResponseDto);
    }

    @ApiOperation(value = "유저 정보 삭제 api", notes = "기존 유저 회원탈퇴")
    @DeleteMapping("/{uid}")
    public void deleteUser(
            @ApiParam(value = "유저 식별자", required = true) @PathVariable Long uid
    ) {
        userService.deleteUser(uid);
    }
}
