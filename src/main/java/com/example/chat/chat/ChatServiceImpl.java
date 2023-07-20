package com.example.chat.chat;

import com.example.chat.chat.dto.ChatDeleteRequestDto;
import com.example.chat.chat.dto.ChatRequestDto;
import com.example.chat.chat.dto.ChatResponseDto;
import com.example.chat.group.Groups;
import com.example.chat.group.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl {
    private final ChatRepository chatRepository;
    private final GroupRepository groupRepository;

    public List<ChatResponseDto> getAllByUid(Long uid) {
        List<Groups> groupsList = groupRepository.findAllByUser_Id(uid);
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        for (Groups groups : groupsList) {
            List<Chat> chatList = chatRepository.findAllByGroups_Id(groups);
            for (Chat chat : chatList) {
                chatResponseDtoList.add(ChatResponseDto.builder()
                        .id(chat.getId())
                        .gid(chat.getGroups().getId())
                        .message(chat.getMessage())
                        .genTime(chat.getGenTime())
                        .endDate(chat.getEndDate())
                        .build());
            }
        }
        return chatResponseDtoList;
    }

    public List<ChatResponseDto> getAllByRoomId(Long roomId) {
        List<Groups> groupsList = groupRepository.findAllByChatRoom_Id(roomId);
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        for (Groups groups : groupsList) {
            List<Chat> chatList = chatRepository.findAllByGroups_Id(groups);
            for (Chat chat : chatList) {
                chatResponseDtoList.add(ChatResponseDto.builder()
                        .id(chat.getId())
                        .gid(chat.getGroups().getId())
                        .message(chat.getMessage())
                        .genTime(chat.getGenTime())
                        .endDate(chat.getEndDate())
                        .build());
            }
        }
        return chatResponseDtoList;
    }

    public List<ChatResponseDto> getAllByUidAndRoomId(Long uid, Long roomId) {
        Groups groups = groupRepository.findByUser_IdAndChatRoom_Id(uid, roomId);
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        List<Chat> chatList = chatRepository.findAllByGroups_Id(groups);
        for (Chat chat : chatList) {
            chatResponseDtoList.add(ChatResponseDto.builder()
                    .id(chat.getId())
                    .gid(chat.getGroups().getId())
                    .message(chat.getMessage())
                    .genTime(chat.getGenTime())
                    .endDate(chat.getEndDate())
                    .build());
        }
        return chatResponseDtoList;
    }

    public ChatResponseDto saveChat(ChatRequestDto chatRequestDto) {
        Chat chat = Chat.builder()
                .message(chatRequestDto.getMessage())
                .groups(chatRequestDto.getGroups())
                .build();

        Chat saveChat = chatRepository.save(chat);

        return ChatResponseDto.builder()
                .id(saveChat.getId())
                .message(saveChat.getMessage())
                .gid(saveChat.getGroups().getId())
                .genTime(saveChat.getGenTime())
                .endDate(saveChat.getEndDate())
                .build();
    }

    public void delete(ChatDeleteRequestDto chatDeleteRequestDto) throws Exception {
        Optional<Chat> optionalChat = chatRepository.findById(chatDeleteRequestDto.getId());

        Chat selectedChat;
        if (optionalChat.isPresent()) {
            selectedChat = optionalChat.get();
            if (selectedChat.getGroups().getId() != chatDeleteRequestDto.getId()) {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }

        chatRepository.deleteById(chatDeleteRequestDto.getId());
    }
}
