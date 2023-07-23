package com.example.chat.permission;

import com.example.chat.permission.dto.PermissionRequestDto;
import com.example.chat.permission.dto.PermissionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionServiceImpl permissionService;

    @GetMapping("/{uid}/{roomId}")
    public ResponseEntity<PermissionResponseDto> getPermission(
            @PathVariable Long uid,
            @PathVariable Long roomId) {
        PermissionResponseDto permissionResponseDto = permissionService.getByUidAndRoomId(uid, roomId);

        return ResponseEntity.status(HttpStatus.OK).body(permissionResponseDto);
    }

    @PostMapping()
    public ResponseEntity<PermissionResponseDto> postPermission(@RequestBody PermissionRequestDto permissionRequestDto) {
        PermissionResponseDto permissionResponseDto = permissionService.savePermission(
                permissionRequestDto.getUid(),
                permissionRequestDto.getRoomId(),
                permissionRequestDto.getState()
        );
        return ResponseEntity.status(HttpStatus.OK).body(permissionResponseDto);
    }

    @PutMapping()
    public ResponseEntity<PermissionResponseDto> putPermission(@RequestBody PermissionRequestDto permissionRequestDto) throws Exception {
        PermissionResponseDto permissionResponseDto = permissionService.changePermission(
                permissionRequestDto.getUid(),
                permissionRequestDto.getRoomId(),
                permissionRequestDto.getState());

        return ResponseEntity.status(HttpStatus.OK).body(permissionResponseDto);
    }

    @DeleteMapping()
    public void deletePermission(@RequestBody PermissionRequestDto permissionRequestDto) {
        permissionService.deletePermission(
                permissionRequestDto.getUid(),
                permissionRequestDto.getRoomId());
    }
}
