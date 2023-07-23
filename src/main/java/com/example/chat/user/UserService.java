package com.example.chat.user;


import com.example.chat.user.dto.UserRequestDto;
import com.example.chat.user.dto.UserResponseDto;

public interface UserService {
    UserResponseDto getUser(Long id);
    UserResponseDto saveUser(UserRequestDto userRequestDto);
    void deleteUser(Long id);
}