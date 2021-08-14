package com.example.devwebtalk.chattingRoom;

import com.example.devwebtalk.dto.ChatDto;
import com.example.devwebtalk.dto.ChatMessageDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.Optional;

/**
 * 2021-07-12
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Slf4j
@RestController
public class ChatController {

    private final UserService userService;

    @Autowired
    public ChatController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 클라이언트가 ../chat 요청 시 sendMessage() 호출 이후 리턴된 객체정보 담아 @SendTo 로 보냄
     * @param message info
     * @return chatDto
     */
    @MessageMapping("/sendMsg")
    @SendTo("/topic/receiveMsg")
    public ChatDto sendMessage(ChatMessageDto message) {
        log.debug("sendMessage : {}", message.getContent());
        // 유저 ID로 유저 이름 세팅
        Optional<User> user = userService.findById(message.getUserId());
        message.setUserName(user.orElseThrow().getName());
        return message.sendMessage();
    }

    /**
     * 유저 접속 시 안내
     * @param user info
     * @return ChatMessageDto
     */
    @MessageMapping("/userEnter/{roomId}")
    @SendTo("/topic/userEnter")
    public ChatMessageDto userEnter(User user, @PathVariable("roomId") Integer roomId) {
        log.info("userEnter : {}", user);
        log.info("roomId : {}", roomId);
        return new ChatMessageDto(HtmlUtils.htmlEscape("[" + user.getName() + "]" + " 님이 입장하셨습니다."));
    }
}
