package com.example.devwebtalk.chattingRoom;

import com.example.devwebtalk.entity.Chat;
import com.example.devwebtalk.entity.ChattingRoom;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.service.ChatService;
import com.example.devwebtalk.service.ChattingRoomService;
import com.example.devwebtalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 2021-07-21
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Controller
public class TestController {

    @Autowired
    UserService userService;
    @Autowired
    ChatService chatService;
    @Autowired
    ChattingRoomService roomService;
    
    @GetMapping("chatTest")
    public String chatTest() {
        return "chat/chatInclude";
    }

    @GetMapping("room")
    public String room(Model model) {
        addTestDataAndModel(model);
        return "chat/room";
    }

    public void addTestDataAndModel(Model model) {
        User user1 = new User("영롱");
        User user2 = new User("다롱");
        userService.save(user1);
        userService.save(user2);
        ChattingRoom room1 = new ChattingRoom(Arrays.asList(user1, user2));
        roomService.save(room1);
        Chat chat1 = new Chat("하이 영롱", user2, room1);
        chatService.save(chat1);
        Chat chat2 = new Chat("하이 다롱", user1, room1);
        chatService.save(chat2);
        Chat chat3 = new Chat("바이", user1, room1);
        chatService.save(chat3);

        model.addAttribute("user", user1);
        model.addAttribute("room", room1);
    }
}
