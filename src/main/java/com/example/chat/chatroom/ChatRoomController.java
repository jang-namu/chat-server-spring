package com.example.chat.chatroom;

import com.example.chat.chatroom.dto.ChatRoomDeleteRequestDto;
import com.example.chat.chatroom.dto.ChatRoomRequestDto;
import com.example.chat.chatroom.dto.ChatRoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomServiceImpl chatRoomService;

    @GetMapping()
    public ResponseEntity<List<ChatRoomResponseDto>> getAllChatRoom() {
        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRoomService.getAllRooms();
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomResponseDtos);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoomResponseDto> getChatRoomByRoomId(@PathVariable Long roomId) throws Exception {
        ChatRoomResponseDto chatRoomResponseDto = chatRoomService.getChatRoom(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomResponseDto);
    }

    @PostMapping("")
    public ResponseEntity<ChatRoomResponseDto> postChatRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto) {
        ChatRoomResponseDto chatRoomResponseDto = chatRoomService.saveChatRoom(chatRoomRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomResponseDto);
    }

    @DeleteMapping()
    public void deleteChatRoom(@RequestBody ChatRoomDeleteRequestDto chatRoomDeleteRequestDto) {
        chatRoomService.deleteChatRoom(chatRoomDeleteRequestDto);
    }
}
