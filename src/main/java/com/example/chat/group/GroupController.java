package com.example.chat.group;

import com.example.chat.group.dto.GroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupServiceImpl groupService;

    @GetMapping("/{uid}")
    public ResponseEntity<List<GroupResponseDto>> getGroupByUid(@PathVariable Long uid) {
        List<GroupResponseDto> groupResponseDtos = groupService.getGroupsByUid(uid);

        return ResponseEntity.status(HttpStatus.OK).body(groupResponseDtos);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<List<GroupResponseDto>> getGroupByRoomId(@PathVariable Long roomId) {
        List<GroupResponseDto> groupResponseDtos = groupService.getGroupsByRoomId(roomId);

        return ResponseEntity.status(HttpStatus.OK).body(groupResponseDtos);
    }

    @GetMapping("/{uid}/{roomId}")
    public ResponseEntity<GroupResponseDto> getGroupByUid(
            @PathVariable Long uid,
            @PathVariable Long roomId) {
        GroupResponseDto groupResponseDto = groupService.getGroupsByUidAndRoomId(uid, roomId);

        return ResponseEntity.status(HttpStatus.OK).body(groupResponseDto);
    }
}
