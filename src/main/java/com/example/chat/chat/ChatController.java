package com.example.chat.chat;

import com.example.chat.chat.dto.ChatDeleteRequestDto;
import com.example.chat.chat.dto.ChatRequestDto;
import com.example.chat.chat.dto.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatServiceImpl chatService;

    @GetMapping("/{uid}")
    public ResponseEntity<List<ChatResponseDto>> getChatByUid(@PathVariable Long uid) {
        List<ChatResponseDto> chatResponseDtos = chatService.getAllByUid(uid);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponseDtos);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<List<ChatResponseDto>> getChatByRoomId(@PathVariable Long roomId) {
        List<ChatResponseDto> chatResponseDtos = chatService.getAllByRoomId(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponseDtos);
    }

    @GetMapping("/{uid}/{roomId}")
    public ResponseEntity<List<ChatResponseDto>> getChatByUid(
            @PathVariable Long uid,
            @PathVariable Long roomId) {
        List<ChatResponseDto> chatResponseDtos = chatService.getAllByUidAndRoomId(uid, roomId);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponseDtos);
    }

    @PostMapping()
    public ResponseEntity<ChatResponseDto> postChat(@RequestBody ChatRequestDto chatRequestDto) {
        ChatResponseDto chatResponseDto = chatService.saveChat(chatRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponseDto);
    }


    @DeleteMapping("")
    public void deleteChat(@RequestBody ChatDeleteRequestDto chatDeleteRequestDto) throws Exception {
        chatService.delete(chatDeleteRequestDto);
    }
}
