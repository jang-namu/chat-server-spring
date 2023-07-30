package com.example.chat.group;

import com.example.chat.group.dto.GroupRequestDto;
import com.example.chat.group.dto.GroupResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Group"})
@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupServiceImpl groupService;

    @ApiOperation(value = "그룹 정보 조회 api", notes = "특정 유저가 포함된 그룹 정보 조회")
    @GetMapping("/user/{uid}")
    public ResponseEntity<List<GroupResponseDto>> getGroupByUid(
            @ApiParam(value = "유저 식별자", required = true) @PathVariable Long uid
    ) {
        List<GroupResponseDto> groupResponseDtos = groupService.getGroupByUid(uid);

        return ResponseEntity.status(HttpStatus.OK).body(groupResponseDtos);
    }

    @ApiOperation(value = "그룹 정보 조회 api", notes = "특정 채팅방에 포함된 모든 유저쌍, 그룹 정보 조회")
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<GroupResponseDto>> getGroupByRoomId(
            @ApiParam(value = "채팅방 식별자", required = true)@PathVariable Long roomId
    ) {
        List<GroupResponseDto> groupResponseDtos = groupService.getGroupByRoomId(roomId);

        return ResponseEntity.status(HttpStatus.OK).body(groupResponseDtos);
    }

    @ApiOperation(value = "그룹 정보 조회 api", notes = "특정 채팅방, 특정 유저의 그룹 정보 조회")
    @GetMapping("/{uid}/{roomId}")
    public ResponseEntity<GroupResponseDto> getGroupByUid(
            @ApiParam(value = "유저 식별자", required = true) @PathVariable Long uid,
            @ApiParam(value = "채팅방 식별자", required = true)@PathVariable Long roomId
    ) {
        GroupResponseDto groupResponseDto = groupService.getGroupByUidAndRoomId(uid, roomId);

        return ResponseEntity.status(HttpStatus.OK).body(groupResponseDto);
    }

    @ApiOperation(value = "그룹(채팅방-유저 쌍) 추가 api", notes = "채팅방에 새로운 유저 추가")
    @PostMapping()
    public ResponseEntity<GroupResponseDto> addUserToRoom(@RequestBody GroupRequestDto groupRequestDto) throws Exception {
        GroupResponseDto groupResponseDto = groupService.addUser(groupRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(groupResponseDto);
    }
}
