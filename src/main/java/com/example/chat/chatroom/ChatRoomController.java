package com.example.chat.chatroom;

import com.example.chat.chatroom.dto.ChatRoomDeleteRequestDto;
import com.example.chat.chatroom.dto.ChatRoomRequestDto;
import com.example.chat.chatroom.dto.ChatRoomResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags={"ChatRoom"})
@RestController
@RequestMapping("/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomServiceImpl chatRoomService;

    @ApiOperation(value = "채팅방 정보 조회 api", notes = "전체 채팅방 정보 조회")
    @GetMapping()
    public ResponseEntity<List<ChatRoomResponseDto>> getAllChatRoom() {
        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRoomService.getAllRooms();
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomResponseDtos);
    }

    @ApiOperation(value = "채팅방 정보 조회 api", notes = "채팅방 식별자로 정보 조회")
    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoomResponseDto> getChatRoomByRoomId(
            @ApiParam(value = "채팅방 식별자", required = true) @PathVariable Long roomId
    ) throws Exception {
        ChatRoomResponseDto chatRoomResponseDto = chatRoomService.getChatRoom(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomResponseDto);
    }

    @ApiOperation(value = "채팅방 생성 api", notes = "신규 채팅방 생성")
    @PostMapping("")
    public ResponseEntity<ChatRoomResponseDto> postChatRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto) {
        ChatRoomResponseDto chatRoomResponseDto = chatRoomService.saveChatRoom(chatRoomRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomResponseDto);
    }

    @ApiOperation(value = "채팅방 삭제 api", notes = "기존 채팅방 삭제")
    @DeleteMapping()
    public void deleteChatRoom(@RequestBody ChatRoomDeleteRequestDto chatRoomDeleteRequestDto) {
        chatRoomService.deleteChatRoom(chatRoomDeleteRequestDto);
    }
}
