package com.example.chat.group;

import com.example.chat.group.dto.GroupRequestDto;
import com.example.chat.group.dto.GroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupServiceImpl groupService;

    @GetMapping("/user/{uid}")
    public ResponseEntity<List<GroupResponseDto>> getGroupByUid(@PathVariable Long uid) {
        List<GroupResponseDto> groupResponseDtos = groupService.getGroupByUid(uid);

        return ResponseEntity.status(HttpStatus.OK).body(groupResponseDtos);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<GroupResponseDto>> getGroupByRoomId(@PathVariable Long roomId) {
        List<GroupResponseDto> groupResponseDtos = groupService.getGroupByRoomId(roomId);

        return ResponseEntity.status(HttpStatus.OK).body(groupResponseDtos);
    }

    @GetMapping("/{uid}/{roomId}")
    public ResponseEntity<GroupResponseDto> getGroupByUid(@RequestBody GroupRequestDto groupRequestDto) {
        GroupResponseDto groupResponseDto = groupService.getGroupByUidAndRoomId(groupRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(groupResponseDto);
    }

    @PostMapping()
    public ResponseEntity<GroupResponseDto> addUserToRoom(@RequestBody GroupRequestDto groupRequestDto) throws Exception {
        GroupResponseDto groupResponseDto = groupService.addUser(groupRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(groupResponseDto);
    }
}
