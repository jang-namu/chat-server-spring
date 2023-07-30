package com.example.chat.chat;

import com.example.chat.chat.dto.ChatRequestDto;
import com.example.chat.chat.dto.ChatResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Chat"})
@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatServiceImpl chatService;

    @ApiOperation(value = "채팅 내역 조회 api", notes = "유저 식별자로 특정 유저의 모든 채팅 내역 조회")
    @GetMapping("/user/{uid}")
    public ResponseEntity<List<ChatResponseDto>> getChatByUid(
            @ApiParam(value = "유저 식별자", required = true) @PathVariable Long uid
    ) {
        List<ChatResponseDto> chatResponseDtos = chatService.getAllByUid(uid);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponseDtos);
    }

    @ApiOperation(value = "채팅 내역 조회 api", notes = "채팅방 식별자로 특정 채팅방의 모든 채팅 내용 조회")
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<ChatResponseDto>> getChatByRoomId(
            @ApiParam(value = "채팅방 식별자", required = true) @PathVariable Long roomId
    ) {
        List<ChatResponseDto> chatResponseDtos = chatService.getAllByRoomId(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponseDtos);
    }

    @ApiOperation(value = "채팅 내역 조회 api", notes = "특정 채팅방에 특정 유저가 작성한 모든 채팅 내역 조회")
    @GetMapping("/{uid}/{roomId}")
    public ResponseEntity<List<ChatResponseDto>> getChatByUid(
            @ApiParam(value = "유저 식별자", required = true) @PathVariable Long uid,
            @ApiParam(value = "채팅방 식별자", required = true) @PathVariable Long roomId
    ) {
        List<ChatResponseDto> chatResponseDtos = chatService.getAllByUidAndRoomId(uid, roomId);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponseDtos);
    }

    @ApiOperation(value = "채팅 생성 api", notes = "특정 채팅방에 유저가 채팅 작성")
    @PostMapping()
    public ResponseEntity<ChatResponseDto> postChat(@RequestBody ChatRequestDto chatRequestDto) throws Exception {
        ChatResponseDto chatResponseDto = chatService.saveChat(chatRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponseDto);
    }

    @ApiOperation(value = "채팅 내용 삭제 api", notes = "특정 채팅방에 특정 유저가 작성한 한 개의 채팅 삭제")
    @DeleteMapping("/{chatId}")
    public void deleteChat(
            @ApiParam(value = "채팅 식별자", required = true) @PathVariable Long chatId
    ) throws Exception {
        chatService.delete(chatId);
    }
}
