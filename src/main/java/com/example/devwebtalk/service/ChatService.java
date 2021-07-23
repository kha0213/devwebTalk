package com.example.devwebtalk.service;

import com.example.devwebtalk.entity.Chat;
import com.example.devwebtalk.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2021-07-21
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    public Long save(Chat chat) {
        Chat c = chatRepository.save(chat);
        return c.getId();
    }

}
