package com.example.chat.chat;

import com.example.chat.group.Group;
import com.example.chat.group.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl {
    private final ChatRepository chatRepository;
    private final GroupRepository groupRepository;

    public List<ChatResponseDto> getAllByUid(Long uid) {
        List<Group> groupList = groupRepository.findAllByUid(uid);
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        for (Group group : groupList) {
            List<Chat> chatList = chatRepository.findAllByGroup(group);
            for (Chat chat : chatList) {
                chatResponseDtoList.add(ChatResponseDto.builder()
                        .id(chat.getId())
                        .gid(chat.getGroup().getId())
                        .message(chat.getMessage())
                        .genTime(chat.getGenTime())
                        .endDate(chat.getEndDate())
                        .build());
            }
        }
        return chatResponseDtoList;
    }

    public List<ChatResponseDto> getAllByRoomId(Long roomId) {
        List<Group> groupList = groupRepository.findAllByRoomId(roomId);
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        for (Group group : groupList) {
            List<Chat> chatList = chatRepository.findAllByGroup(group);
            for (Chat chat : chatList) {
                chatResponseDtoList.add(ChatResponseDto.builder()
                        .id(chat.getId())
                        .gid(chat.getGroup().getId())
                        .message(chat.getMessage())
                        .genTime(chat.getGenTime())
                        .endDate(chat.getEndDate())
                        .build());
            }
        }
        return chatResponseDtoList;
    }

    public List<ChatResponseDto> getAllByUidAndRoomId(Long uid, Long roomId) {
        List<Group> groupList = groupRepository.findAllByUidAndRoomId(uid, roomId);
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        for (Group group : groupList) {
            List<Chat> chatList = chatRepository.findAllByGroup(group);
            for (Chat chat : chatList) {
                chatResponseDtoList.add(ChatResponseDto.builder()
                        .id(chat.getId())
                        .gid(chat.getGroup().getId())
                        .message(chat.getMessage())
                        .genTime(chat.getGenTime())
                        .endDate(chat.getEndDate())
                        .build());
            }
        }
        return chatResponseDtoList;
    }

    public ChatResponseDto saveChat(ChatRequestDto chatRequestDto) {
        Chat chat = Chat.builder()
                .message(chatRequestDto.getMessage())
                .group(chatRequestDto.getGroup())
                .build();

        Chat saveChat = chatRepository.save(chat);

        return ChatResponseDto.builder()
                .id(saveChat.getId())
                .message(saveChat.getMessage())
                .gid(saveChat.getGroup().getId())
                .genTime(saveChat.getGenTime())
                .endDate(saveChat.getEndDate())
                .build();
    }


}
