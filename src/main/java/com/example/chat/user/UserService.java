package com.example.chat.user;


public interface UserService {
    UserResponseDto getUser(Long id);
    UserResponseDto saveUser(UserRequestDto userRequestDto);
    void deleteUser(Long id);
}