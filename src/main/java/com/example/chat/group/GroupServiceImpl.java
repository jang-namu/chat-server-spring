package com.example.chat.group;

import com.example.chat.group.dto.GroupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl {
    private final GroupRepository groupRepository;

    public List<GroupResponseDto> getGroupsByUid(Long uid) {
        List<GroupResponseDto> groupResponseDtos = new ArrayList<>();

        List<Groups> groupsList = groupRepository.findAllByUser_Id(uid);
        for (Groups groups : groupsList) {
            groupResponseDtos.add(
                    GroupResponseDto.builder()
                            .id(groups.getId())
                            .uid(groups.getUser().getId())
                            .roomId(groups.getChatRoom().getId())
                            .build()
            );
        }

        return groupResponseDtos;
    }

    public List<GroupResponseDto> getGroupsByRoomId(Long roomId) {
        List<GroupResponseDto> groupResponseDtos = new ArrayList<>();

        List<Groups> groupsList = groupRepository.findAllByChatRoom_Id(roomId);
        for (Groups groups : groupsList) {
            groupResponseDtos.add(
                    GroupResponseDto.builder()
                            .id(groups.getId())
                            .uid(groups.getUser().getId())
                            .roomId(groups.getChatRoom().getId())
                            .build()
            );
        }

        return groupResponseDtos;
    }

    public GroupResponseDto getGroupsByUidAndRoomId(Long uid, Long roomId) {
        Groups selectedGroups = groupRepository.findByUser_IdAndChatRoom_Id(uid, roomId);

        GroupResponseDto groupResponseDto = GroupResponseDto.builder()
                .id(selectedGroups.getId())
                .uid(selectedGroups.getUser().getId())
                .roomId(selectedGroups.getChatRoom().getId())
                .build();

        return groupResponseDto;
    }
}
