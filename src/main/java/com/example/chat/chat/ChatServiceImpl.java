package com.example.chat.chat;

import com.example.chat.chat.dto.ChatDeleteRequestDto;
import com.example.chat.chat.dto.ChatRequestDto;
import com.example.chat.chat.dto.ChatResponseDto;
import com.example.chat.group.Group;
import com.example.chat.group.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl {
    private final ChatRepository chatRepository;
    private final GroupRepository groupRepository;

    public List<ChatResponseDto> getAllByUid(Long uid) {
        List<Group> groupList = groupRepository.findAllByUser_Id(uid);
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        for (Group group : groupList) {
            List<Chat> chatList = chatRepository.findAllByGroup_Id(group.getId());
            for (Chat chat : chatList) {
                chatResponseDtoList.add(ChatResponseDto.builder()
                        .id(chat.getId())
                        .gid(chat.getGroup().getId())
                        .message(chat.getMessage())
                        .genTime(chat.getGenTime())
                        .build());
            }
        }
        return chatResponseDtoList;
    }

    public List<ChatResponseDto> getAllByRoomId(Long roomId) {
        List<Group> groupList = groupRepository.findAllByChatRoom_Id(roomId);
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        for (Group group : groupList) {
            List<Chat> chatList = chatRepository.findAllByGroup_Id(group.getId());
            for (Chat chat : chatList) {
                chatResponseDtoList.add(ChatResponseDto.builder()
                        .id(chat.getId())
                        .gid(chat.getGroup().getId())
                        .message(chat.getMessage())
                        .genTime(chat.getGenTime())
                        .build());
            }
        }
        return chatResponseDtoList;
    }

    public List<ChatResponseDto> getAllByUidAndRoomId(Long uid, Long roomId) {
        Group group = groupRepository.findByUser_IdAndChatRoom_Id(uid, roomId);
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        List<Chat> chatList = chatRepository.findAllByGroup_Id(group.getId());
        for (Chat chat : chatList) {
            chatResponseDtoList.add(ChatResponseDto.builder()
                    .id(chat.getId())
                    .gid(chat.getGroup().getId())
                    .message(chat.getMessage())
                    .genTime(chat.getGenTime())
                    .build());
        }
        return chatResponseDtoList;
    }

    public ChatResponseDto saveChat(ChatRequestDto chatRequestDto) throws Exception {
        Optional<Group> selectedGroup = groupRepository.findById(chatRequestDto.getGid());

        Chat chat;
        if (selectedGroup.isPresent()) {
            chat = Chat.builder()
                    .message(chatRequestDto.getMessage())
                    .group(selectedGroup.get())
                    .genTime(LocalDateTime.now())
                    .endDate(LocalDateTime.now().plusYears(1L))
                    .build();
        } else {
            throw new Exception();
        }

        Chat saveChat = chatRepository.save(chat);

        return ChatResponseDto.builder()
                .id(saveChat.getId())
                .message(saveChat.getMessage())
                .gid(saveChat.getGroup().getId())
                .genTime(saveChat.getGenTime())
                .build();
    }

    public void delete(ChatDeleteRequestDto chatDeleteRequestDto) throws Exception {
        Optional<Chat> optionalChat = chatRepository.findById(chatDeleteRequestDto.getId());

        Chat selectedChat;
        if (optionalChat.isPresent()) {
            selectedChat = optionalChat.get();
            chatRepository.deleteById(selectedChat.getId());
        } else {
            throw new Exception();
        }

    }
}
