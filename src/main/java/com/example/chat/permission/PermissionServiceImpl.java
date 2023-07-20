package com.example.chat.permission;

import com.example.chat.group.Groups;
import com.example.chat.group.GroupRepository;
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
        Groups groups = groupRepository.findByUser_IdAndChatRoom_Id(uid, roomId);

        Permission selectedPermission = permissionRepository.getById(groups.getId());

        return PermissionResponseDto.builder()
                .gid(selectedPermission.getGroups().getId())
                .state(selectedPermission.getState())
                .build();
    }

    public PermissionResponseDto savePermission(Long uid, Long roomId, Byte state) {
        Permission savedPermission = permissionRepository.save(
                Permission.builder()
                        .groups(groupRepository.findByUser_IdAndChatRoom_Id(uid, roomId))
                        .state(state)
                        .build()
        );

        return PermissionResponseDto.builder()
                .gid(savedPermission.getGroups().getId())
                .state(savedPermission.getState())
                .build();
    }

    public PermissionResponseDto changePermission(Long uid, Long roomId, Byte state) throws Exception {
        Groups groups = groupRepository.findByUser_IdAndChatRoom_Id(uid, roomId);

        Optional<Permission> changedPermission = permissionRepository.findById(groups.getId());

        Permission updatedPermission;
        if (changedPermission.isPresent()) {
            Permission permission = changedPermission.get();
            permission.setState(state);

            updatedPermission = permissionRepository.save(permission);
        } else {
            throw new Exception();
        }

        return PermissionResponseDto.builder()
                .gid(updatedPermission.getGroups().getId())
                .state(updatedPermission.getState())
                .build();
    }

    public void deletePermission(Long uid, Long roomId) {
        Groups groups = groupRepository.findByUser_IdAndChatRoom_Id(uid, roomId);
        permissionRepository.deleteById(groups.getId());
    }
}
