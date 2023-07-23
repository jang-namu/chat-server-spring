package com.example.chat.chatroom;

import com.example.chat.chatroom.dto.ChatRoomDeleteRequestDto;
import com.example.chat.chatroom.dto.ChatRoomRequestDto;
import com.example.chat.chatroom.dto.ChatRoomResponseDto;
import com.example.chat.group.Groups;
import com.example.chat.group.GroupRepository;
import com.example.chat.permission.dto.PermissionResponseDto;
import com.example.chat.permission.PermissionServiceImpl;
import com.example.chat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl {

    private final ChatRoomRepository chatRoomRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    private final PermissionServiceImpl permissionService;

    public List<ChatRoomResponseDto> getAllRooms() {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
        List<ChatRoomResponseDto> chatRoomResponseDtos = new ArrayList<>();

        for (ChatRoom chatRoom : chatRoomList) {
            chatRoomResponseDtos.add(
                    ChatRoomResponseDto.builder()
                            .id(chatRoom.getId())
                            .title(chatRoom.getTitle())
                            .adminId(chatRoom.getAdminId())
                            .build()
            );
        }

        return chatRoomResponseDtos;
    }

    public ChatRoomResponseDto getChatRoom(Long roomId) throws Exception {
        Optional<ChatRoom> selectedChatRoom = chatRoomRepository.findById(roomId);

        ChatRoom chatRoom;
        if (selectedChatRoom.isPresent()) {
            chatRoom = selectedChatRoom.get();
        } else {
            throw new Exception();
        }

        return ChatRoomResponseDto.builder()
                .id(chatRoom.getId())
                .title(chatRoom.getTitle())
                .adminId(chatRoom.getAdminId())
                .build();
    }

    public ChatRoomResponseDto saveChatRoom(ChatRoomRequestDto chatRoomRequestDto) {
        ChatRoom chatRoom = ChatRoom.builder()
                .adminId(chatRoomRequestDto.getAdminId())
                .title(chatRoomRequestDto.getTitle())
                .genTime(LocalDateTime.now())
                .build();

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        groupRepository.save(new Groups(savedChatRoom.getId(),
                userRepository.getById(chatRoomRequestDto.getAdminId()),
                savedChatRoom));

        byte adminState = 1;    // enum 타입 사용?
        permissionService.savePermission(savedChatRoom.getAdminId(), savedChatRoom.getId(), adminState);

        return ChatRoomResponseDto.builder()
                .id(savedChatRoom.getId())
                .adminId(savedChatRoom.getAdminId())
                .title(savedChatRoom.getTitle())
                .build();
    }

    public void deleteChatRoom(ChatRoomDeleteRequestDto chatRoomDeleteRequestDto) {
        //퍼미션 확인 후 삭제
        PermissionResponseDto permissionResponseDto = permissionService.getByUidAndRoomId(
                chatRoomDeleteRequestDto.getAdminId(),
                chatRoomDeleteRequestDto.getId());

        if (permissionResponseDto.getState() != 1) {
            return;
        }

        chatRoomRepository.deleteById(chatRoomDeleteRequestDto.getId());
        groupRepository.deleteByUser_IdAndChatRoom_Id(
                chatRoomDeleteRequestDto.getAdminId(),
                chatRoomDeleteRequestDto.getId()
        );
    }
}
