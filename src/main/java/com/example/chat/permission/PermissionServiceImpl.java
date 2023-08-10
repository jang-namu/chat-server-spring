package com.example.chat.permission;

import com.example.chat.group.Group;
import com.example.chat.group.GroupRepository;
import com.example.chat.permission.dto.PermissionRequestDto;
import com.example.chat.permission.dto.PermissionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl {

    private final PermissionRepository permissionRepository;
    private final GroupRepository groupRepository;

    public PermissionResponseDto getByUidAndRoomId(Long uid, Long roomId) {
        Group group = groupRepository.findByUser_IdAndChatRoom_Id(uid, roomId);

        Permission selectedPermission = permissionRepository.getById(group.getId());

        return PermissionResponseDto.builder()
                .gid(selectedPermission.getGroup().getId())
                .state(selectedPermission.getState())
                .build();
    }



    public PermissionResponseDto savePermission(PermissionRequestDto permissionRequestDto) {
        Permission savedPermission = permissionRepository.save(
                Permission.builder()
                        .group(
                                groupRepository.findByUser_IdAndChatRoom_Id(
                                        permissionRequestDto.getUid(),
                                        permissionRequestDto.getRoomId()
                                )
                        )
                        .state(permissionRequestDto.getState())
                        .build()
        );

        return PermissionResponseDto.builder()
                .gid(savedPermission.getGroup().getId())
                .state(savedPermission.getState())
                .build();
    }



    public PermissionResponseDto changePermission(PermissionRequestDto permissionRequestDto) throws Exception {
        Group group = groupRepository.findByUser_IdAndChatRoom_Id(
                permissionRequestDto.getUid(),
                permissionRequestDto.getRoomId());

        Optional<Permission> changedPermission = permissionRepository.findById(group.getId());

        Permission updatedPermission;
        if (changedPermission.isPresent()) {
            Permission permission = changedPermission.get();
            permission.setState(permission.getState());

            updatedPermission = permissionRepository.save(permission);
        } else {
            throw new Exception();
        }

        return PermissionResponseDto.builder()
                .gid(updatedPermission.getGroup().getId())
                .state(updatedPermission.getState())
                .build();
    }

    public void deletePermission(PermissionRequestDto permissionRequestDto) {
        if (permissionRequestDto.getState() != 1) {
            return;
        }

        Group group = groupRepository.findByUser_IdAndChatRoom_Id(
                permissionRequestDto.getUid(),
                permissionRequestDto.getRoomId());

        permissionRepository.deleteById(group.getId());
    }
}
