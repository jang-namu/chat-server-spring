package com.example.chat.user;

import com.example.chat.user.dto.UserRequestDto;
import com.example.chat.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtos);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long uid) {
        UserResponseDto userResponseDto = userService.getUser(uid);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @PostMapping()
    public ResponseEntity<UserResponseDto> postUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto savedUserResponseDto = userService.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(savedUserResponseDto);
    }

    @DeleteMapping("/{uid}")
    public void deleteUser(@PathVariable Long uid) {
        userService.deleteUser(uid);
    }
}
