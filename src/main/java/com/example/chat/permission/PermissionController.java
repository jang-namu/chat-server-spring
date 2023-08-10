package com.example.chat.permission;

import com.example.chat.permission.dto.PermissionRequestDto;
import com.example.chat.permission.dto.PermissionResponseDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Permission"})
@ApiResponses(
        value = {
                @ApiResponse(code = 200, message = "OK")
                , @ApiResponse(code = 201, message = "Created")
                , @ApiResponse(code = 400, message = "Bad Request")
                , @ApiResponse(code = 401, message = "Unauthorized")
                , @ApiResponse(code = 403, message = "Forbidden")
                , @ApiResponse(code = 404, message = "Not Found")
                , @ApiResponse(code = 500, message = "Internal Server Error")
        }
)
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionServiceImpl permissionService;

    @ApiOperation(value = "권한 조회 api", notes = "특정 채팅방에 특정 유저 권한 조회")
    @GetMapping("/{uid}/{roomId}")
    public ResponseEntity<PermissionResponseDto> getPermission(
            @ApiParam(value = "유저 식별자", required = true) @PathVariable Long uid,
            @ApiParam(value = "채팅방 식별자", required = true)@PathVariable Long roomId
    ) {
        PermissionResponseDto permissionResponseDto = permissionService.getByUidAndRoomId(uid, roomId);

        return ResponseEntity.status(HttpStatus.OK).body(permissionResponseDto);
    }

    @ApiOperation(value = "권한 부여 api", notes = "유저에게 채팅방에 대한 권한 부여")
    @PostMapping()
    public ResponseEntity<PermissionResponseDto> postPermission(@RequestBody PermissionRequestDto permissionRequestDto) {
        PermissionResponseDto permissionResponseDto = permissionService.savePermission(permissionRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(permissionResponseDto);
    }

    @ApiOperation(value = "권한 변경 api", notes = "유저의 채팅방 권한 내용 변경")
    @PutMapping()
    public ResponseEntity<PermissionResponseDto> putPermission(@RequestBody PermissionRequestDto permissionRequestDto) throws Exception {
        PermissionResponseDto permissionResponseDto = permissionService.changePermission(permissionRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(permissionResponseDto);
    }

    @ApiOperation(value = "권한 삭제 api", notes = "채팅방에 대한 유저 권한 삭제")
    @DeleteMapping()
    public void deletePermission(@RequestBody PermissionRequestDto permissionRequestDto) {
        permissionService.deletePermission(permissionRequestDto);
    }
}
