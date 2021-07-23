package com.example.devwebtalk.service;

import com.example.devwebtalk.entity.Chat;
import com.example.devwebtalk.entity.ChattingRoom;
import com.example.devwebtalk.repository.ChattingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2021-07-21
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Service
public class ChattingRoomService {
    @Autowired
    ChattingRoomRepository roomRepository;

    public Long save(ChattingRoom room) {
        room.makeName();
        ChattingRoom c = roomRepository.save(room);
        return c.getId();
    }
}
