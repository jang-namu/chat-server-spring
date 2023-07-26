package com.example.chat.group;

import com.example.chat.chatroom.ChatRoom;
import com.example.chat.chatroom.ChatRoomRepository;
import com.example.chat.chatroom.ChatRoomServiceImpl;
import com.example.chat.group.dto.GroupRequestDto;
import com.example.chat.group.dto.GroupResponseDto;
import com.example.chat.user.User;
import com.example.chat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    public List<GroupResponseDto> getGroupByUid(Long uid) {
        List<GroupResponseDto> groupResponseDtos = new ArrayList<>();

        List<Group> groupList = groupRepository.findAllByUser_Id(uid);
        for (Group group : groupList) {
            groupResponseDtos.add(
                    GroupResponseDto.builder()
                            .id(group.getId())
                            .uid(group.getUser().getId())
                            .roomId(group.getChatRoom().getId())
                            .build()
            );
        }

        return groupResponseDtos;
    }

    public List<GroupResponseDto> getGroupByRoomId(Long roomId) {
        List<GroupResponseDto> groupResponseDtos = new ArrayList<>();

        List<Group> groupList = groupRepository.findAllByChatRoom_Id(roomId);
        for (Group group : groupList) {
            groupResponseDtos.add(
                    GroupResponseDto.builder()
                            .id(group.getId())
                            .uid(group.getUser().getId())
                            .roomId(group.getChatRoom().getId())
                            .build()
            );
        }

        return groupResponseDtos;
    }

    public GroupResponseDto getGroupByUidAndRoomId(GroupRequestDto groupRequestDto) {
        Group selectedGroup = groupRepository.findByUser_IdAndChatRoom_Id(
                groupRequestDto.getUid(),
                groupRequestDto.getRoomId());

        GroupResponseDto groupResponseDto = GroupResponseDto.builder()
                .id(selectedGroup.getId())
                .uid(selectedGroup.getUser().getId())
                .roomId(selectedGroup.getChatRoom().getId())
                .build();

        return groupResponseDto;
    }

    public GroupResponseDto addUser(GroupRequestDto groupRequestDto) throws Exception {
        Optional<User> optionalUser = userRepository.findById(groupRequestDto.getUid());
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(groupRequestDto.getRoomId());

        Group savedGroup;
        if (optionalUser.isPresent() && optionalChatRoom.isPresent()) {
            Group group = Group.builder()
                    .user(optionalUser.get())
                    .chatRoom(optionalChatRoom.get())
                    .build();

            savedGroup = groupRepository.save(group);
        } else {
            throw new Exception();
        }

        return GroupResponseDto.builder()
                .id(savedGroup.getId())
                .uid(savedGroup.getUser().getId())
                .roomId(savedGroup.getChatRoom().getId())
                .build();
    }
}
